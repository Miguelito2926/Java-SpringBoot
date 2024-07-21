package com.ednaldo.ecommerce.domain.repository;

import com.ednaldo.ecommerce.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
