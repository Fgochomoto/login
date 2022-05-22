package br.com.projetofinal.login.services;

import br.com.projetofinal.login.entity.Cliente;
import br.com.projetofinal.login.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    private MyUserDetailsService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cliente) throws UsernameNotFoundException {
        Cliente user = clienteRepository.findByUsername(cliente);

        if(cliente.equalsIgnoreCase("admin")){
            return new User("admin", "admin",
                    new ArrayList<>());
        }else {

            return new User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        }
    }
}