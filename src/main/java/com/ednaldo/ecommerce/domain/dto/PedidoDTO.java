package com.ednaldo.ecommerce.domain.dto;

import com.ednaldo.ecommerce.validation.NotEmptyList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
@NoArgsConstructor
@ToString
@Setter
@Getter


public class PedidoDTO {

    @NotNull(message = "Informe o código do cliente.")
    private Long cliente;

    @NotNull(message = "Campo total do pedido é obrigatório.")
    private BigDecimal total;

    @NotEmptyList(message = "O pedido não pode ser concluído sem itens.")
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
