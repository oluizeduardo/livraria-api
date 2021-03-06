package br.com.livraria.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private LocalDate dataLancamento;
	
	@Column(name = "num_paginas")
	private int numeroPaginas;
	
	@ManyToOne
	private Autor autor;

	
	public Livro(String titulo, LocalDate dataLancamento, int numeroPaginas, Autor autor) 
	{
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.numeroPaginas = numeroPaginas;
		this.autor = autor;
	}
	
	
	public void atualizarInformacoes(String titulo, LocalDate dataLancamento, 
			int numeroPaginas, Integer autorId) 
	{		
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.numeroPaginas = numeroPaginas;
		this.autor.setId(autorId);
	}
	
}