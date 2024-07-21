package com.ednaldo.ecommerce.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Setter
    @Getter
    private Integer quantidade;

}
