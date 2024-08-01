package com.ednaldo.ecommerce.api.controller;

import com.ednaldo.ecommerce.domain.entity.Cliente;
import com.ednaldo.ecommerce.domain.repository.ClienteRepository;
import com.ednaldo.ecommerce.exception.ObjetoNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/clientes")
@Api("Api Clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping
    @ApiOperation("Obter todos os clientes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Clientes listados com sucesso.")
    })
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @GetMapping(value = "/filter")
    @ApiOperation("Filtrar clientes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Clientes filtrados com sucesso.")
    })
    public List<Cliente> filter(@ApiParam("Filtros para a busca de clientes") Cliente cliente) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Cliente> example = Example.of(cliente, exampleMatcher);
        return clienteRepository.findAll(example);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado."),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado.")
    })
    public Cliente getClienteById(@ApiParam("ID do cliente") @PathVariable Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ObjetoNotFoundException("Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salvar um novo cliente na base de dados")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public Cliente insert(@RequestBody @ApiParam("Informações do cliente") @Valid Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deletar um cliente")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cliente deletado com sucesso."),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado.")
    })
    public void delete(@ApiParam("ID do cliente") @PathVariable Long id) {
        clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ObjetoNotFoundException("Cliente não encontrado"));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Atualizar os dados de um cliente")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cliente atualizado com sucesso."),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado."),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public void clienteUpdate(@RequestBody @ApiParam("Informações atualizadas do cliente") @Valid Cliente cliente,
                              @ApiParam("ID do cliente") @PathVariable Long id) {
        clienteRepository.findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ObjetoNotFoundException("Cliente não encontrado"));
    }
}

