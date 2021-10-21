package br.com.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sun.istack.NotNull;

import br.com.livraria.dto.AtualizacaoLivroFormDTO;
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
	
	@PutMapping
	@ApiOperation("Atualizar um livro")
	public ResponseEntity<LivroDTO> atualizar(@RequestBody @Valid AtualizacaoLivroFormDTO dto) 
	{
		LivroDTO livroDTOAtualizado = livroService.atualizar(dto);
		return ResponseEntity.ok(livroDTOAtualizado);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation("Deletar um livro")
	public ResponseEntity<LivroDTO> remover(@PathVariable @NotNull Integer id) 
	{
		livroService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("{id}")
	@ApiOperation("Detalhar um livro")
	public ResponseEntity<LivroDTO> detalhar(@PathVariable @NotNull Integer id) 
	{
		LivroDTO dto = livroService.detalhar(id);
		return ResponseEntity.ok(dto);
	}
	
}
