package br.com.livraria.dto;

import com.sun.istack.NotNull;

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
public class AtualizacaoLivroFormDTO extends LivroFormDTO {

	@NotNull
	private Integer id;
	
}
