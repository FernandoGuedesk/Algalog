package com.algaworks.algalog.api.controller;

import java.util.List;

import com.algaworks.algalog.domain.service.CatalogoClienteService;
import com.algaworks.algalog.domain.repository.ClienteRespository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algaworks.algalog.domain.model.Cliente;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private ClienteRespository clienteRespository;
	private CatalogoClienteService catalogoClienteService;

	@GetMapping
	public List<Cliente> listar() {
		 return clienteRespository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> busca(@PathVariable Long clienteId){
		return clienteRespository.findById(clienteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalogoClienteService.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
		if (!clienteRespository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);

		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if (!clienteRespository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		catalogoClienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
}
