package br.com.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class AutorFormDTO {
		
	@NotBlank(message = "'Nome' is required!")
	@Size(min=2, max = 30, message = "Name should be between 2 and 30 chars.")
	private String nome;
	
	@PastOrPresent
	private LocalDate dataNascimento;
	
	@NotBlank(message = "'nacionalidade' is required!")
	@NotBlank
	private String nacionalidade;
	
	private String curriculo;
}
