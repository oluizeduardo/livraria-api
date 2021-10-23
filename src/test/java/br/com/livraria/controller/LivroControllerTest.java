package br.com.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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
class LivroControllerTest {

	/**
	 * Objeto que simula as requisições HTTP.
	 */
	@Autowired
	private MockMvc mvc;
	
	@Test
	void naoDeveriaCadastrarLivroComDadosIncompletos() throws Exception 
	{
		
		String json = "{}";
		
		mvc
			.perform(post("/livros")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(status().isBadRequest());		
	}
	
	
	@Test
	void deveriaCadastrarLivroComDadosCompletos() throws Exception 
	{
		
		String jsonAutor = "{"
								+ "\"nome\":\"Fyodor Dostoevsky\","
								+ "\"dataNascimento\":\"1821-11-11\","
								+ "\"nacionalidade\":\"Russia\","
								+ "\"curriculo\":\"Autor de livros de romance psicológico.\""
							+ "}";

	
		mvc
			.perform(post("/autores")
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonAutor))
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"))
			.andExpect(content().json(jsonAutor));
		
			
		String jsonLivro = "{"
						+ "\"autorId\":2,"
						+ "\"dataLancamento\":\"1866-11-11\","
						+ "\"numeroPaginas\":500,"
						+ "\"titulo\":\"Crime e Castigo\""
					+ "}";
		
		String jsonLivroRetorno = "{"
				+ "\"dataLancamento\":\"1866-11-11\","
				+ "\"numeroPaginas\":500,"
				+ "\"titulo\":\"Crime e Castigo\""
			+ "}";
		
		mvc
			.perform(post("/livros")
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonLivro))
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"))
			.andExpect(content().json(jsonLivroRetorno));		
	}

}
