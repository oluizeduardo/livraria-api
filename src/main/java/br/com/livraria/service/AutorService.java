package br.com.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.livraria.dto.AutorDTO;
import br.com.livraria.dto.AutorFormDTO;
import br.com.livraria.model.Autor;

@Service
public class AutorService {

	private List<Autor> autores = new ArrayList<Autor>();
	private ModelMapper modelMapper = new ModelMapper();

	public List<AutorDTO> listar() 
	{
		return autores.stream().map(t -> modelMapper.map(t, AutorDTO.class)).collect(Collectors.toList());
	}

	public void cadastrar(AutorFormDTO dto) 
	{
		Autor autor = modelMapper.map(dto, Autor.class);
		
		autores.add(autor);
	}

}
