package br.com.livraria.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "autores")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private LocalDate dataNascimento;
	private String nacionalidade;
	private String curriculo;
	
	
	public Autor(String nome, LocalDate dataNascimento, 
			String nacionalidade, String curriculo) {
		
		atualizarInformacoes(nome, dataNascimento, nacionalidade, curriculo);
	}	
	
	public void atualizarInformacoes(String nome, LocalDate dataNascimento, 
			String nacionalidade, String curriculo) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.curriculo = curriculo;
	}

}