package br.com.livraria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemLivrariaDTO {

	private String nomeAutor;
	private Long quantidadeLivros;
	private Double percentual;
}
