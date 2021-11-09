package com.projetowinning.vilayara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetowinning.vilayara.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {

}
