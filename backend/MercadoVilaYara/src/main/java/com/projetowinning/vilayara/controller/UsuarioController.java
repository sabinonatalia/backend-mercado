package com.projetowinning.vilayara.controller;

import com.projetowinning.vilayara.model.Usuario;
import com.projetowinning.vilayara.model.UsuarioLogin;
import com.projetowinning.vilayara.repository.UsuariosRepository;
import com.projetowinning.vilayara.service.UsuarioService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	
	@Autowired
	UsuariosRepository usuariosRepository;
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll(){
		return ResponseEntity.ok(usuariosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> GetById(@PathVariable long id){
		return usuariosRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
	return usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
	.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
	return ResponseEntity.status(HttpStatus.CREATED)
	.body(usuarioService.cadastrarUsuario(usuario));
	}
	
	
	
	@GetMapping("/list") 
	public List<Usuario> ListaUsuarios() {
		return usuariosRepository.findAll();
	}
	
	@PutMapping("/usuario/{id}")
	public Usuario atualizarUsuario(@RequestBody Usuario usuario) {
		return usuariosRepository.save(usuario);
	}
	
	@DeleteMapping("/usuario")
	public void deletarUsuario(@RequestBody Usuario usuario) {
		usuariosRepository.delete(usuario);
	}
	
	@GetMapping("/usuario/list/nome")
	@Query(value = "SELECT * FROM Usuario ORDER BY nome \n-- #pageable\n",
		countQuery = "SELECT count(*) FROM Usuario",
		nativeQuery = true)
		public Collection<Usuario> OrdenarListaPorNome(@Param("nome") String nome){
		return usuariosRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	@GetMapping("/usuario/list/id")
	@Query(value = "SELECT * FROM Usuario ORDER BY id \n-- #pageable\n",
		countQuery = "SELECT count(*) FROM Usuario",
		nativeQuery = true)
		public Collection<Usuario> OrdenarListaPorID(@Param("id") Long id){
		return usuariosRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
}
