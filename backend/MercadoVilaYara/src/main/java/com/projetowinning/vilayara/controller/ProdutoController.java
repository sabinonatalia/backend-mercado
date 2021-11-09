package com.projetowinning.vilayara.controller;

import com.projetowinning.vilayara.model.Produto;
import com.projetowinning.vilayara.repository.ProdutosRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@DeleteMapping("/produto")
	public void deletarProduto(@RequestBody Produto produto) {
		ProdutosRepository.delete(produto);
	}

}
