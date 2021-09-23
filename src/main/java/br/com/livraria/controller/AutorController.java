package br.com.livraria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.dto.AutorDTO;
import br.com.livraria.dto.AutorFormDTO;
import br.com.livraria.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	private AutorService service;
	
	
	@GetMapping
	public List<AutorDTO> listar() 
	{
		return service.listar();
	}
	
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid AutorFormDTO dto) 
	{
		service.cadastrar(dto);
	}
	
}
