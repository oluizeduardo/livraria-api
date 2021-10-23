package br.com.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livraria.dto.ItemLivrariaDTO;
import br.com.livraria.repository.AutorRepository;

@Service
public class RelatorioService {

	@Autowired
	private AutorRepository autorRepository;
	
	public List<ItemLivrariaDTO> relatorioLivraria()
	{	
		return autorRepository.relatorioDeLivrosPorAutor();
	}
	
}
