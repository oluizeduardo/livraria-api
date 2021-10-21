package br.com.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.livraria.dto.AtualizacaoAutorFormDTO;
import br.com.livraria.dto.AutorDTO;
import br.com.livraria.dto.AutorFormDTO;
import br.com.livraria.model.Autor;
import br.com.livraria.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	private ModelMapper modelMapper = new ModelMapper();
	

	public Page<AutorDTO> listar(Pageable paginacao) 
	{
		Page<Autor> autores = autorRepository.findAll(paginacao);
		return autores.map(a -> modelMapper.map(a, AutorDTO.class));
	}

	@Transactional
	public AutorDTO cadastrar(AutorFormDTO dto) 
	{
		Autor autor = modelMapper.map(dto, Autor.class);
		autor.setId(null);
		autorRepository.save(autor);
		
		return modelMapper.map(autor, AutorDTO.class);
	}	
	
	@Transactional
	public AutorDTO atualizar(@Valid AtualizacaoAutorFormDTO dto) {
		Autor autor = autorRepository.getById(dto.getId());		
		
		autor.atualizarInformacoes(dto.getNome(), 
				dto.getDataNascimento(),
				dto.getNacionalidade(),
				dto.getCurriculo());
		
		return modelMapper.map(autor, AutorDTO.class);
	}

	@Transactional
	public void remover(Integer id) 
	{
		autorRepository.deleteById(id);
	}

	public AutorDTO detalhar(Integer id) 
	{
		Autor autor = autorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		return modelMapper.map(autor, AutorDTO.class);
	}
	
}
