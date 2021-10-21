package br.com.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.livraria.dto.AtualizacaoLivroFormDTO;
import br.com.livraria.dto.LivroDTO;
import br.com.livraria.dto.LivroFormDTO;
import br.com.livraria.model.Livro;
import br.com.livraria.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	private ModelMapper modelMapper = new ModelMapper();

	
	public Page<LivroDTO> listar(Pageable paginacao) 
	{
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(livro -> modelMapper.map(livro, LivroDTO.class));
	}

	@Transactional
	public LivroDTO cadastrar(LivroFormDTO dto) 
	{
		Livro livro = modelMapper.map(dto, Livro.class);
		livro.setId(null);
		livroRepository.save(livro);
		
		return modelMapper.map(livro, LivroDTO.class);
	}

	@Transactional
	public LivroDTO atualizar(@Valid AtualizacaoLivroFormDTO dto) 
	{
		Livro livro = livroRepository.getById(dto.getId());		
		
		livro.atualizarInformacoes(dto.getTitulo(), dto.getDataLancamento(), 
				dto.getNumeroPaginas(), dto.getAutorId());

		return modelMapper.map(livro, LivroDTO.class);
	}

	
	@Transactional
	public void remover(Integer id) 
	{
		livroRepository.deleteById(id);
	}

	public LivroDTO detalhar(Integer id) 
	{
		Livro livro = livroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

		return modelMapper.map(livro, LivroDTO.class);
	}

}
