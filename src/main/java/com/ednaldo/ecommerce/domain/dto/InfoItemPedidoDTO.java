package com.ednaldo.ecommerce.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class InfoItemPedidoDTO {

    private String descrição;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
