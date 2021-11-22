package br.com.livraria.infra;

public interface EnviadorDeEmail {

	public void enviarEmail(String destinatario, String assunto, String mensagem);
}