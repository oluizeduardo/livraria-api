package br.com.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.dto.ItemLivrariaDTO;
import br.com.livraria.service.RelatorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController

//Documentação Swagger.
@Api(tags = "Relatórios")

@RequestMapping("/relatorios")
public class RelatoriosController {

	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/livraria")
	@ApiOperation("Relatório da livraria")
	public List<ItemLivrariaDTO> relatorioLivraria()
	{
		return relatorioService.relatorioLivraria();
	}
}
