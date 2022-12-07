package com.algaworks.algalog.domain.repository;

import ch.qos.logback.core.net.server.Client;
import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRespository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome (String nome);
    List<Cliente> findByNomeContaining (String nome);
    Optional<Cliente> findByEmail(String email);

}
