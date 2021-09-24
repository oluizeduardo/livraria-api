package br.com.livraria.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO {

	private String titulo;
	private LocalDate dataLancamento;
	private int numeroPaginas;
	private AutorDTO autor;
}
