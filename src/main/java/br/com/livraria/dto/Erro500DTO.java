package br.com.livraria.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Erro500DTO {

	private LocalDateTime timestamp;
	private String erro;
	private String mensagem;
	private String path;
	
}
