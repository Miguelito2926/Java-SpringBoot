package com.ednaldo.ecommerce.domain.entity;

import com.ednaldo.ecommerce.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total", precision = 7, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

    @Getter
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
}
