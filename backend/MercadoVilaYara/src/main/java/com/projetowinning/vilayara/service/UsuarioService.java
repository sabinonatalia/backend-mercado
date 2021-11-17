package com.projetowinning.vilayara.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.projetowinning.vilayara.model.Usuario;
import com.projetowinning.vilayara.model.UsuarioLogin;
import com.projetowinning.vilayara.repository.UsuariosRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuariosRepository repository;

	public Usuario cadastrarUsuario(Usuario email) {

		if (repository.findByEmail(email.getEmail()).isPresent()) // Verificar se o usuário existe (email)
			return null;

		// Encriptografar a senha
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		String senhaEnconder = enconder.encode(email.getSenha());
		email.setSenha(senhaEnconder);

		return repository.save(email);

	}

	
	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());

		if (usuario.isPresent())
			if (enconder.matches(user.get().getSenha(), usuario.get().getSenha())) { // Para checar se é a mesma senha
																						// cadastrada

				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encondeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII"))); // Criptografia da
																										// senha
				String authHeader = "Basic " + new String(encondeAuth); // Token

				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setTipo(usuario.get().getTipo());
				user.get().setEmail(usuario.get().getEmail());

				return user;
			}

		return null;
	}
}

