package com.ednaldo.ecommerce.domain.repository;

import com.ednaldo.ecommerce.domain.entity.Cliente;
import com.ednaldo.ecommerce.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Set<Pedido> findByCliente(Cliente cliente);
}
