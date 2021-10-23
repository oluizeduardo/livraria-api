package br.com.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.livraria.dto.AutorDTO;
import br.com.livraria.dto.AutorFormDTO;
import br.com.livraria.repository.AutorRepository;

//Carrega as funções do mockito antes de inicializar o JUnit.
@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

	@Mock
	private AutorRepository repository;
	
	@InjectMocks
	private AutorService service;

	
	@Test
	void deveriaCadastrarUmAutor() 
	{
		
		AutorFormDTO autor = criaAutor("Fyodor Dostoevsky", LocalDate.of(1821, 11, 11), "Russia", 
				"Autor de livros de românce psicológico.");

		AutorDTO autorCadastrado = service.cadastrar(autor);
		
		
		// Checa se o método 'save' foi chamado.
		Mockito.verify(repository).save(Mockito.any());
		
		assertEquals(autor.getNome(), autorCadastrado.getNome());
		assertEquals(autor.getDataNascimento(), autorCadastrado.getDataNascimento());
		assertEquals(autor.getNacionalidade(), autorCadastrado.getNacionalidade());
		assertEquals(autor.getCurriculo(), autorCadastrado.getCurriculo());		
	}
	
	private AutorFormDTO criaAutor(String nome, LocalDate dataNascimento, String nacionalidade, String curriculo) {
		return new AutorFormDTO(nome, dataNascimento, nacionalidade, curriculo);
	}

}
