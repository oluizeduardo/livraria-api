package br.com.livraria.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDTO {

	private String nome;
	private LocalDate dataNascimento;
	private String nacionalidade;
	private String curriculo;
}
