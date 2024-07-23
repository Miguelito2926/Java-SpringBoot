package com.ednaldo.ecommerce.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class InfoPedidoDTO {

    private Long codigo;
    private String dataPedido;
    private String cpf;
    private String nome;
    private BigDecimal total;
    private List<InfoItemPedidoDTO> infoItemPedidosDTO;
}
