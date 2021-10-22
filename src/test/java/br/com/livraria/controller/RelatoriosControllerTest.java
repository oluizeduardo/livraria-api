package br.com.livraria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.livraria.model.Autor;
import br.com.livraria.model.Livro;
import br.com.livraria.repository.AutorRepository;
import br.com.livraria.repository.LivroRepository;


//Carrega as funções do Spring antes de inicializar o JUnit.
@ExtendWith(SpringExtension.class)

//Carrega todas as configurações do projeto, não somente a camada de persistência.
@SpringBootTest

//Configura o MockMvc para injeção nos testes.
@AutoConfigureMockMvc

//Indica qual arquivo .propoerties deve ser carregado. Gerar banco de teste.
@ActiveProfiles("test")

//Executa em contexto transacional. Faz o rollback ao final de cada teste.
@Transactional
class RelatoriosControllerTest {

	/**
	 * Objeto que simula as requisições HTTP.
	 */
	@Autowired
	private MockMvc mvc; 
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	
	
	@Test
	void deveriaEmitirRelatorio() throws Exception 
	{
		
		Autor autor1 = new Autor("Fyodor Dostoevsky", 
								LocalDate.of(1821, 11, 11), 
								"Russia", 
								"Autor de livros de românce psicológico.");
		
		Autor autor2 = new Autor("Jorge Amado", 
				LocalDate.of(1912, 8, 10), 
				"Brasil", 
				"Romancista bahiano, teve um dos seus livros transformado em filme.");
		
		autorRepository.save(autor1);	
		autorRepository.save(autor2);
		
		Livro livro1 = new Livro("Crime e Castigo", LocalDate.of(1866, 1, 1), 500, autor1);
		Livro livro2 = new Livro("Guerra e Paz", LocalDate.of(1867, 1, 1), 1225, autor1);
		Livro livro3 = new Livro("Capitães da Areia", LocalDate.of(1937, 1, 1), 230, autor2);
		livroRepository.save(livro1);
		livroRepository.save(livro2);
		livroRepository.save(livro3);
		
		
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get("/relatorios/livraria"))
			    .andReturn();
			 
		String json = "["
				+ "{"
					+ "\"nomeAutor\":\"Fyodor Dostoevsky\","
					+ "\"quantidadeLivros\":2,"
					+ "\"percentual\":66.67"
				+ "},"
				+ "{"
					+ "\"nomeAutor\":\"Jorge Amado\","
					+ "\"quantidadeLivros\":1,"
					+ "\"percentual\":33.33"
				+ "}]";
		
		assertEquals(json, mvcResult.getResponse().getContentAsString());
	}

}
