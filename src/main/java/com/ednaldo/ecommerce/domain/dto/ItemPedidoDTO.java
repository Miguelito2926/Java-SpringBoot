package com.ednaldo.ecommerce.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ItemPedidoDTO {
    private Long produtoId;
    private Integer quantidade;
}
