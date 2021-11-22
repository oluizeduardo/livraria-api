package br.com.livraria.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
//Carrega esta classe somente em produção.
//Veja a classe EnviadorDeEmailFake para rodar em ambiente de teste e desenvolvimento.
@Profile("prod")
public class EnviadorDeEmailReal implements EnviadorDeEmail {

	@Autowired
	private JavaMailSender mailSender;
	
	
	@Async
	public void enviarEmail(String destinatario, String assunto, String mensagem) {
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(destinatario);
		email.setSubject(assunto);
		email.setText(mensagem);
		
		try {

			mailSender.send(email);

		} catch (Exception e) {
			System.err.println("ERRO AO ENVIAR EMAIL AO NOVO USUÁRIO!!\n"+e.getMessage());
		}
		
	}
	
}
