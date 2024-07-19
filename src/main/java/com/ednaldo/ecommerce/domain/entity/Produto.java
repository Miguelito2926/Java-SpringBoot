package com.ednaldo.ecommerce.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String descricao;
    private BigDecimal preco;

    public Long getId() {
        return id;
    }

    public Produto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Produto setPreco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }
}
