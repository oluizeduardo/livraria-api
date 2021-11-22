package br.com.livraria.infra;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
// Carrega esta classe somente nos perfis de teste e default.
// Veja a classe EnviadorDeEmailReal para rodar em produção.
@Profile({"default","test"})
public class EnviadorDeEmailFake implements EnviadorDeEmail {
	
	
	@Async
	public void enviarEmail(String destinatario, String assunto, String mensagem) 
	{
		System.out.println("\nENVIANDO EMAIL DE TESTE...\n\n");
		System.out.println(mensagem);	
	}
	
}
