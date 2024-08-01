package com.ednaldo.ecommerce.api.controller;

import com.ednaldo.ecommerce.domain.entity.Produto;
import com.ednaldo.ecommerce.domain.repository.ProdutoRepository;
import com.ednaldo.ecommerce.exception.ObjetoNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/produtos")
@Api("Api Produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @GetMapping
    @ResponseStatus(OK)
    @ApiOperation("Obter a lista de todos os produtos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produtos listados com sucesso.")
    })
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @GetMapping(value = "/filter")
    @ResponseStatus(OK)
    @ApiOperation("Obter a lista de produtos com filtro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produtos filtrados com sucesso.")
    })
    public List<Produto> filterProduto(@ApiParam("Parâmetros de filtro") Produto produto) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Produto> example = Example.of(produto, exampleMatcher);
        return produtoRepository.findAll(example);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Criar um novo produto")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto criado com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public Produto insertProduto(@RequestBody @ApiParam("Dados do novo produto") @Valid Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    @ApiOperation("Obter detalhes de um produto por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto encontrado."),
            @ApiResponse(code = 404, message = "Produto não encontrado para o ID informado.")
    })
    public Produto findById(@ApiParam("ID do produto") @PathVariable Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ObjetoNotFoundException("Produto não encontrado"));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Excluir um produto por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Produto excluído com sucesso."),
            @ApiResponse(code = 404, message = "Produto não encontrado para o ID informado.")
    })
    public void delete(@ApiParam("ID do produto") @PathVariable Long id) {
        produtoRepository.findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ObjetoNotFoundException("Produto não encontrado"));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Atualizar os dados de um produto existente")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Produto atualizado com sucesso."),
            @ApiResponse(code = 404, message = "Produto não encontrado para o ID informado."),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public void updateProduto(@ApiParam("ID do produto") @PathVariable Long id,
                              @RequestBody @ApiParam("Dados atualizados do produto") @Valid Produto produto) {
        produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtoRepository.save(produto);
                    return produtoExistente;
                }).orElseThrow(() -> new ObjetoNotFoundException("Produto não encontrado"));
    }
}
