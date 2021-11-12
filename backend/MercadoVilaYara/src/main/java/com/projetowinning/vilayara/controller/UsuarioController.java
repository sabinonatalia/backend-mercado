package com.projetowinning.vilayara.controller;


import com.projetowinning.vilayara.model.Usuario;
import com.projetowinning.vilayara.repository.UsuariosRepository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController()
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	
	@Autowired
	UsuariosRepository UsuariosRepository;

	
	@PostMapping("/usuario")
	public void salvarUsuario(@RequestBody Usuario usuario) {
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
	
	@GetMapping("/usuario/list/nome")
	@Query(value = "SELECT * FROM Usuario ORDER BY nome \n-- #pageable\n",
		countQuery = "SELECT count(*) FROM Usuario",
		nativeQuery = true)
		public Collection<Usuario> OrdenarListaPorNome(@Param("nome") String nome){
		return UsuariosRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	@GetMapping("/usuario/list/id")
	@Query(value = "SELECT * FROM Usuario ORDER BY id \n-- #pageable\n",
		countQuery = "SELECT count(*) FROM Usuario",
		nativeQuery = true)
		public Collection<Usuario> OrdenarListaPorID(@Param("id") Long id){
		return UsuariosRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
}
