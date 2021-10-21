package br.com.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.livraria.dto.LivroDTO;
import br.com.livraria.dto.LivroFormDTO;
import br.com.livraria.service.LivroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController

//Documentação Swagger.
@Api(tags = "Livros")

@RequestMapping("/livros")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	ModelMapper modelMapper = new ModelMapper();
	
	
	@GetMapping
	@ApiOperation("Listar livros")
	public Page<LivroDTO> listar(@PageableDefault(size = 5) Pageable paginacao) 
	{
		return livroService.listar(paginacao);
	}
	

	@PostMapping
	@ApiOperation("Cadastrar novo livro")
	public ResponseEntity<LivroDTO> cadastrar(@RequestBody @Valid LivroFormDTO dto, UriComponentsBuilder uriBuilder) 
	{
		LivroDTO livroDTO = livroService.cadastrar(dto);
		
		URI uri = uriBuilder.path("/livros/{id}")
				.buildAndExpand(livroDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(livroDTO);
	}
	
}
