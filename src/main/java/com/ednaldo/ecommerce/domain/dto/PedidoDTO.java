package com.ednaldo.ecommerce.domain.dto;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor

public class PedidoDTO {

    private Long clienteId;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;
}
