package com.ednaldo.ecommerce.api.controller;

import com.ednaldo.ecommerce.domain.entity.Cliente;
import com.ednaldo.ecommerce.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> list = clienteRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Cliente> clienteesixste = clienteRepository.findById(id);
        if (clienteesixste.isPresent()) {
            clienteRepository.delete(clienteesixste.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> clienteUpdate(@RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteRepository
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
