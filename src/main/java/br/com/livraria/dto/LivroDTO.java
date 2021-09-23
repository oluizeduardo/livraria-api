package br.com.livraria.dto;

import java.time.LocalDate;

import br.com.livraria.model.Autor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO {

	private String titulo;
	private LocalDate dataLancamento;
	private int numeroPaginas;
	private Autor autor;
}
