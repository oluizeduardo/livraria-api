package br.com.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;

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
public class LivroFormDTO {
		
	@NotBlank
	@Size(min=10, message = "'titulo' should have at least 10 chars.")
	private String titulo;
	
	@PastOrPresent(message = "'dataLancamento' should be past or present.")
	private LocalDate dataLancamento;
	
	@Min(100)
	private int numeroPaginas;
	
	@NotNull(message = "'autor' should not be null.")
	@JsonAlias("autor_id")
	private Integer autorId;
}
