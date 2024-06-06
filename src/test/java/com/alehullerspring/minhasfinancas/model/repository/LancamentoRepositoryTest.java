package com.alehullerspring.minhasfinancas.model.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alehullerspring.minhasfinancas.model.entity.Lancamento;
import com.alehullerspring.minhasfinancas.model.enums.StatusLancamento;
import com.alehullerspring.minhasfinancas.model.enums.TipoLancamento;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	LancamentoRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmLancamento() {
		Lancamento lancamento = criarLancamento();
		lancamento = repository.save(lancamento);
		
		Assertions.assertThat(lancamento.getId()).isNotNull();
	}

	@Test
	public void deveDeletarUmLancamento() {
		Lancamento lancamento = criarLancamento();
		entityManager.persist(lancamento);
		lancamento = entityManager.find(Lancamento.class, lancamento.getId());
		
		repository.delete(lancamento);
		
		Lancamento lancamentoInexistente = entityManager.find(Lancamento.class, lancamento.getId());
		
		Assertions.assertThat(lancamentoInexistente).isNull();
	}
	
	private Lancamento criarLancamento() {
		return Lancamento.builder().ano(2019).mes(1).descricao("lancamento qualquer").valor(BigDecimal.valueOf(10)).tipo(TipoLancamento.RECEITA).status(StatusLancamento.PENDENTE).dataCadastro(LocalDate.now()).build();
	}
}
