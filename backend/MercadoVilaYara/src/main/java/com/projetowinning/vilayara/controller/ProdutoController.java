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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	ProdutosRepository ProdutosRepository;

	// buscar todos os produtos
	@GetMapping
	public ResponseEntity<List<Produto>> ListaProdutos() {
		return ResponseEntity.ok(ProdutosRepository.findAll());
	}

	// cadastrar um novo produto
	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutosRepository.save(produto));
	}

	// atualizar um produto
	@PutMapping("/produto/{id}")
	public ResponseEntity<Produto> atualizaProduto(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.OK).body(ProdutosRepository.save(produto));
	}

	// buscar produto pelo nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getById(@PathVariable String nome) {
		return ResponseEntity.ok(ProdutosRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	// deletar um produto
	@DeleteMapping("/produto/{id}")
	public void deletarProduto(@RequestBody Produto produto) {
		ProdutosRepository.delete(produto);
	}

	@GetMapping("/list/nome")
	@Query(value = "SELECT * FROM Produto ORDER BY nome \n-- #pageable\n", countQuery = "SELECT count(*) FROM Produto", nativeQuery = true)
	public Collection<Produto> OrdenarListaPorNome(@Param("nome") String nome) {
		return ProdutosRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));

	}

	@GetMapping("/list/cat")
	@Query(value = "SELECT * FROM Produto ORDER BY categoria \n-- #pageable\n", countQuery = "SELECT count(*) FROM Produto", nativeQuery = true)
	public Collection<Produto> OrdenarListaPorCategoria(@Param("categoria") String categoria) {
		return ProdutosRepository.findAll(Sort.by(Sort.Direction.ASC, "categoria"));

	}

	@GetMapping("/list/preco")
	@Query(value = "SELECT * FROM Produto ORDER BY preco \n-- #pageable\n", countQuery = "SELECT count(*) FROM Produto", nativeQuery = true)
	public Collection<Produto> OrdenarListaPorPreco(@Param("preco") BigDecimal preco) {
		return ProdutosRepository.findAll(Sort.by(Sort.Direction.ASC, "preco"));

	}

}
