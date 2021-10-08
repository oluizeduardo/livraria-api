package br.com.livraria.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AutorDTO {

	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private String nacionalidade;
	private String curriculo;
}
