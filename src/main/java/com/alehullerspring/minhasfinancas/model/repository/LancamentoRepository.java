package com.alehullerspring.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alehullerspring.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
