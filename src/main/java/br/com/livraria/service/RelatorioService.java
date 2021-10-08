package br.com.livraria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livraria.dto.ItemLivrariaDTO;
import br.com.livraria.repository.AutorRepository;

@Service
public class RelatorioService {

	@Autowired
	private AutorRepository repository;
	
	public List<ItemLivrariaDTO> relatorioLivraria()
	{
//		return repository.relatorioLivraria();
		return new ArrayList<ItemLivrariaDTO>();
	}
	
}
