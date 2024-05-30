package com.alehullerspring.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alehullerspring.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//Optional<Usuario> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
