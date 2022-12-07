package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogoClienteService {
    private ClienteRespository clienteRespository;

    public Cliente buscar(Long clienteId) {
        return clienteRespository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRespository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

           if (emailEmUso) {
               throw  new NegocioException("Já existe um cliente cadastrado com este e-mail.");
           }

        return clienteRespository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        clienteRespository.deleteById(clienteId);
    }
}
