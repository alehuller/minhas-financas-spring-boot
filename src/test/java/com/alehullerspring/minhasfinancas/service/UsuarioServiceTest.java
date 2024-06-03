package com.alehullerspring.minhasfinancas.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alehullerspring.minhasfinancas.exception.RegraNegocioException;
import com.alehullerspring.minhasfinancas.model.entity.Usuario;
import com.alehullerspring.minhasfinancas.model.repository.UsuarioRepository;
import com.alehullerspring.minhasfinancas.service.impl.UsuarioServiceImpl;

//Testes unitarios

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	UsuarioService service;
	UsuarioRepository repository;
	
	@BeforeEach
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test
	public void deveValidarEmail() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false); //repository.deleteAll();
		//Configura o comportamento do mock repository para retornar false quando o metodo existsbyemail for chamado com qualquer string
		
		service.validarEmail("email@email.com");
	}
	
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true); //Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build(); e repository.save(usuario); 
		//sempre quando receber uma string, ele retorna true
		
		assertThrows(RegraNegocioException.class, () -> {
			service.validarEmail("email@email.com");
		});
	}
}
