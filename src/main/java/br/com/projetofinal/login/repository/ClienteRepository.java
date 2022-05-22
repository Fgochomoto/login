package br.com.projetofinal.login.repository;

import br.com.projetofinal.login.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByUsername(String username);
}
