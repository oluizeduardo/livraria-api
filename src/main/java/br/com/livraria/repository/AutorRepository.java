package br.com.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.livraria.dto.ItemLivrariaDTO;
import br.com.livraria.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
	
	@Query("SELECT new br.com.livraria.dto.ItemLivrariaDTO( "
			// nome
			+ "a.nome, "
			// quantidade de livros
			+ "count(li.autor.id), "
			// percentual
			+ "ROUND((count(li.autor.id) / "
				+ "(SELECT count(li2.autor.id) FROM Livro li2)) * 100.0, 2)) "
			
			+ "FROM Livro li "
			
			// Junção da entidade Livro com Autor.
			+ "JOIN li.autor a "			
			+ "WHERE li.autor.id = a.id "
			
			// agrupamento de livros por autor
			+ "GROUP BY li.autor.id")
	List<ItemLivrariaDTO> relatorioDeLivrosPorAutor();
	
}
