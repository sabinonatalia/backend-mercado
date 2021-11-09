package com.projetowinning.vilayara.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name =  "tb_usuario")
public class Usuario {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	 
	 @NotNull
	 @Size(min = 5, max = 100, message = "Este campo precisa de no mínimo 5 e no máximo 100 caracteres!")
     private String nome;
   
	 @NotNull
	 @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
     private String senha;
	 
	 @NotNull
	 @Email
	 private String email;

	 //Relaciomento
	 @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
	 @JsonIgnoreProperties("usuario")
	 private List<Produto> produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	 
	 
}
