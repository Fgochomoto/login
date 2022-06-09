package br.com.projetofinal.login.services;

import br.com.projetofinal.login.entity.Cliente;
import br.com.projetofinal.login.repository.ClienteRepository;
import br.com.projetofinal.login.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private  ClienteService clienteService;

    private MyUserDetailsService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cliente) {
        Cliente user = clienteRepository.findByUsername(cliente);

        if(cliente.equalsIgnoreCase("admin")){
            return new User("admin", "admin",
                    new ArrayList<>());
        }else{
            if (!user.getIsLocked()){
                if (user.getTentativas() < 3) {
                    user.setTentativas(user.getTentativas()+1);
                    clienteRepository.save(user);
                    return new User(user.getUsername(), user.getPassword(),
                            new ArrayList<>());
                } else {
                    user.setIsLocked(true);
                    clienteRepository.save(user);
                    try {
                        throw new AuthorizationServiceException("Por questões de segurança, seu usuário foi bloqueado1");
                    } catch (Exception e) {
                        throw e;
                    }

                }
            }else{
                try {
                    throw new AuthorizationServiceException("Por questões de segurança, seu usuário foi bloqueado2");
                } catch (Exception e) {
                    throw new AuthorizationServiceException("Por questões de segurança, seu usuário foi bloqueado22");
                }
            }
        }
    }
}