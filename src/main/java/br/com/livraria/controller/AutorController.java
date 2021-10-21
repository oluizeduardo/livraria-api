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

import br.com.livraria.dto.AutorDTO;
import br.com.livraria.dto.AutorFormDTO;
import br.com.livraria.service.AutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController

//Documentação Swagger.
@Api(tags = "Autores")

@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	private AutorService autorService;
	ModelMapper modelMapper = new ModelMapper();
	
	
	@GetMapping
	@ApiOperation("Listar autores")
	public Page<AutorDTO> listar(@PageableDefault(size = 5) Pageable paginacao) 
	{
		return autorService.listar(paginacao);
	}
	
	
	@PostMapping
	@ApiOperation("Cadastrar novo autor")
	public ResponseEntity<AutorDTO> cadastrar(@RequestBody @Valid AutorFormDTO dto, UriComponentsBuilder uriBuilder) 
	{
		AutorDTO autorDTO = autorService.cadastrar(dto);
		
		URI uri = uriBuilder.path("/autores/{id}")
				.buildAndExpand(autorDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(autorDTO);
	}
	
}
