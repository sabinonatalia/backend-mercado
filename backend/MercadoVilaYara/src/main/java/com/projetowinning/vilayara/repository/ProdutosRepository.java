package com.projetowinning.vilayara.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetowinning.vilayara.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
}
