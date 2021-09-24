package br.com.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class LivroFormDTO {
		
	@NotBlank
	@Size(min=10, message = "'titulo' should have at least 10 chars.")
	private String titulo;
	
	@PastOrPresent(message = "'dataLancamento' should be past or present.")
	private LocalDate dataLancamento;
	
	@Min(100)
	private int numeroPaginas;
	
	@NotNull(message = "'autor' should not be null.")
	private AutorFormDTO autor;
}
