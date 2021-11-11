package com.projetowinning.vilayara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetowinning.vilayara.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

}