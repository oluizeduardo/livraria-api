package br.com.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.livraria.dto.ItemLivrariaDTO;
import br.com.livraria.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
	
	@Query("SELECT new br.com.livraria.dto.ItemLivrariaDTO( "
			+ "a.nome as nomeAutor, "
			+ "count(li.autor_id) as quantidadeLivros, "
			+ "ROUND((count(li.autor_id) / "
				+ "(SELECT count(liTemp.titulo) FROM br.com.livraria.dto.LivroDTO liTemp))*100.0, 2) as percentual) "
			+ "FROM br.com.livraria.dto.LivroDTO li "
			+ "JOIN br.com.livraria.dto.AutorDTO a "
			+ "WHERE li.autor_id = a.id "
			+ "GROUP BY li.autor_id")
	List<ItemLivrariaDTO> relatorioLivraria();
	
}
