package br.com.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.livraria.dto.LivroDTO;
import br.com.livraria.dto.LivroFormDTO;
import br.com.livraria.model.Livro;

@Service
public class LivroService {

	private List<Livro> livros = new ArrayList<Livro>();
	private ModelMapper modelMapper = new ModelMapper();

	public List<LivroDTO> listar() 
	{
		return livros.stream().map(t -> modelMapper.map(t, LivroDTO.class)).collect(Collectors.toList());
	}

	public void cadastrar(LivroFormDTO dto) 
	{
		Livro livro = modelMapper.map(dto, Livro.class);
		
		livros.add(livro);
	}

}
