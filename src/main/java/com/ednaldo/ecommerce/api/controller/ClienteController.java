package com.ednaldo.ecommerce.api.controller;

import com.ednaldo.ecommerce.domain.entity.Cliente;
import com.ednaldo.ecommerce.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
      Optional<Cliente> cliente = clienteRepository.findById(id);
      if (cliente.isPresent()) {
          return ResponseEntity.ok( cliente.get() );
      }
      return ResponseEntity.notFound().build();
    }
}
