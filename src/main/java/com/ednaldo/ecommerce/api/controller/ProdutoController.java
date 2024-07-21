package com.ednaldo.ecommerce.api.controller;

import com.ednaldo.ecommerce.domain.entity.Produto;
import com.ednaldo.ecommerce.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    @ResponseStatus(OK)
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @GetMapping(value = "/filter")
    @ResponseStatus(OK)
    public List<Produto> filterProduto(Produto produto) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(produto, exampleMatcher);
        return produtoRepository.findAll(example);

    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto insertProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public Produto findById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        produtoRepository.findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtoRepository.save(produto);
                    return produtoExistente;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }
}