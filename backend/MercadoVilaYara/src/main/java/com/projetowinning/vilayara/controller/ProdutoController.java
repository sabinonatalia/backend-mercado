package com.projetowinning.vilayara.controller;

import com.projetowinning.vilayara.model.Produto;
import com.projetowinning.vilayara.repository.ProdutosRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController()
@RequestMapping(value = "/api")
public class ProdutoController {
	
	@Autowired
	ProdutosRepository ProdutosRepository;

	@PostMapping("/produto")
	public void salvarProduto(@RequestBody Produto produto) {
		ProdutosRepository.save(produto);
	}
	
	@GetMapping("/produto") 
	public List<Produto> ListaProdutos() {
		return ProdutosRepository.findAll();
	}
	
	@PutMapping("/produto/{id}")
	public Produto atualizaProduto(@RequestBody Produto produto) {
		return ProdutosRepository.save(produto);
	}
	
	@GetMapping("/produto/list/nome")
	@Query(value = "SELECT * FROM Produto ORDER BY nome \n-- #pageable\n",
		countQuery = "SELECT count(*) FROM Produto",
		nativeQuery = true)
		public Collection<Produto> OrdenarListaPorNome(@Param("nome") String nome){
		return ProdutosRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	
	}
	
	@GetMapping("/produto/list/cat")
	@Query(value = "SELECT * FROM Produto ORDER BY categoria \n-- #pageable\n",
		countQuery = "SELECT count(*) FROM Produto",
		nativeQuery = true)
		public Collection<Produto> OrdenarListaPorCategoria(@Param("categoria") String categoria){
		return ProdutosRepository.findAll(Sort.by(Sort.Direction.ASC, "categoria"));
	
	}
	
	
	@GetMapping("/produto/list/preco")
	@Query(value = "SELECT * FROM Produto ORDER BY preco \n-- #pageable\n",
		countQuery = "SELECT count(*) FROM Produto",
		nativeQuery = true)
		public Collection<Produto> OrdenarListaPorPreco(@Param("preco") BigDecimal preco){
		return ProdutosRepository.findAll(Sort.by(Sort.Direction.ASC, "preco"));
	
	}	
	
	@DeleteMapping("/produto")
	public void deletarProduto(@RequestBody Produto produto) {
		ProdutosRepository.delete(produto);
	}

}
