package br.com.livraria.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
	
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	private Integer perfilId;
}
