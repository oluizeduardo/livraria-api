package br.com.livraria.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.livraria.model.Autor;
import br.com.livraria.model.Livro;

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
class LivroRepositoryTest {

	@Autowired
	private LivroRepository livroRepository;
	
	@Test
	void deveriaCadastrarUmLivro() {
		Autor autor1 = criaAutor("Fyodor Dostoevsky", LocalDate.of(1821, 11, 11), "Russia", 
				"Autor de livros de românce psicológico.");;

		Livro livro = criaLivro("Crime e Castigo", LocalDate.of(1866, 1, 1), 500, autor1);

		Livro livroCadastrado = livroRepository.save(livro);
		
		assertNotNull(livroCadastrado);
		assertEquals(livro.getTitulo(), livroCadastrado.getTitulo());
		assertEquals(livro.getDataLancamento(), livroCadastrado.getDataLancamento());
		assertEquals(livro.getNumeroPaginas(), livroCadastrado.getNumeroPaginas());
		assertEquals(livro.getAutor(), livroCadastrado.getAutor());
	}
	
	private Autor criaAutor(String nome, LocalDate dataNascimento, String nacionalidade, String curriculo) {
		return new Autor(nome, dataNascimento, nacionalidade, curriculo);
	}

	private Livro criaLivro(String titulo, LocalDate dataLancamento, int numPaginas, Autor autor) {
		return new Livro(titulo, dataLancamento, numPaginas, autor);
	}

}
