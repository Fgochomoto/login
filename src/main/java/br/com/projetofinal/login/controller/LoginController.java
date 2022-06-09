package br.com.projetofinal.login.controller;

import br.com.projetofinal.login.entity.Cliente;
import br.com.projetofinal.login.models.AuthenticationRequest;
import br.com.projetofinal.login.models.AuthenticationResponse;
import br.com.projetofinal.login.repository.ClienteRepository;
import br.com.projetofinal.login.services.MyUserDetailsService;
import br.com.projetofinal.login.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Usuário logado";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (AuthenticationException e) {
			throw new Exception("Incorrect username or password", e);
		}
		catch (AuthorizationServiceException e){
			throw new Exception(e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Boolean isLocked = false;
		String cliente = authenticationRequest.getUsername();
		if(!"admin".equalsIgnoreCase(cliente))
		{
			Cliente user = clienteRepository.findByUsername(cliente);
			isLocked = user.getIsLocked();
			user.setTentativas(0);
			clienteRepository.save(user);
		}

		AuthenticationResponse teste = new AuthenticationResponse(jwt, isLocked);
		System.out.println(teste);
		ResponseEntity response = new ResponseEntity(teste, HttpStatus.OK);
		return response;
	}

}

