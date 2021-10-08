package br.com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.livraria.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

	/*@Query("SELECT new br.com.livraria.dto.ItemLivrariaDTO("
			// nome
			+ "autor.nome, "
			// quantidade
			+ "sum(autor.nome), "
			// percentual
			+ "sum(autor.nome) * 1.0 / (SELECT sum(a.nome) FROM Autores a) *1.0) "
			
			+ "FROM Autores autores "
			+ "GROUP BY autor.nome")
	List<ItemLivrariaDTO> relatorioLivraria();*/
	
}
