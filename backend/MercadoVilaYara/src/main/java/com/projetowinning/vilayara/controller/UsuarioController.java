package com.projetowinning.vilayara.controller;

import com.projetowinning.vilayara.model.Usuario;
import com.projetowinning.vilayara.repository.UsuariosRepository;

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


public class UsuarioController {

	
	@Autowired
	UsuariosRepository UsuariosRepository;

	@PostMapping("/usuario")
	public void salvarProduto(@RequestBody Usuario usuario) {
		UsuariosRepository.save(usuario);
	}
	
	@GetMapping("/usuario") 
	public List<Usuario> ListaUsuarios() {
		return UsuariosRepository.findAll();
	}
	
	@PutMapping("/usuario/{id}")
	public Usuario atualizarUsuario(@RequestBody Usuario usuario) {
		return UsuariosRepository.save(usuario);
	}
	
	@DeleteMapping("/usuario")
	public void deletarUsuario(@RequestBody Usuario usuario) {
		UsuariosRepository.delete(usuario);
	}

}
