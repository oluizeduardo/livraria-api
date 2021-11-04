package br.com.livraria.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class UsuarioFormDTO {
	
	@NotBlank
	@Size(min=2, max = 40)
	private String nome;
	
	@NotBlank
	@Size(min=2, max = 20)
	private String login;
	
//	@NotNull
//	private Integer perfilId;
}
