package com.ednaldo.ecommerce.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Cliente cliente;
    private LocalDate dataPedido;
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public Pedido setId(Long id) {
        this.id = id;
        return this;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pedido setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public Pedido setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Pedido setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }
}
