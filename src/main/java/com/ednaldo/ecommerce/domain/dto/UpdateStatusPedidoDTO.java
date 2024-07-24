package com.ednaldo.ecommerce.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateStatusPedidoDTO {

    private String novoStatus;
}
