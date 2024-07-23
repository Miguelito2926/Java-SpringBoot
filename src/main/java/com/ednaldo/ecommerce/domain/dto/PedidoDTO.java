package com.ednaldo.ecommerce.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
@NoArgsConstructor
@ToString
@Setter
@Getter


public class PedidoDTO {

    private Long cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;

    public PedidoDTO(Long cliente, BigDecimal total, List<ItemPedidoDTO> items) {
        this.cliente = cliente;
        this.total = total;
        this.items = items;
    }

    public Long getCliente() {
        return cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ItemPedidoDTO> getItems() {
        return items;
    }
}
