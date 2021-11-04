package br.com.livraria.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.livraria.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
/**
 * Classe de geração e manipulação de Tokens para autenticação do usuário.
 * 
 * @author Luiz Eduardo Costa
 *
 */
@Service
public class TokenService {

	/**
	 * Senha de 256 caracteres.
	 * Lê essa propriedade no arquivo application.properties.
	 */
	@Value("${jjwt.secret}")
	private String secret;
	
	
	public String gerarToken(Authentication authentication) 
	{	
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		
		return Jwts
				.builder()
				.setSubject(usuarioLogado.getId().toString())
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean isValid(String token) 
	{
		try {
			
			Jwts
			.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Integer extrairIdUsuario(String token) 
	{
		Claims claims = Jwts
		.parser()
		.setSigningKey(secret)
		.parseClaimsJws(token).getBody();
		
		return Integer.parseInt(claims.getSubject());		
	}

}
