package br.com.livraria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "perfis")
/**
 * Classe responsável por criar os perfis de usuários no sistema.
 * 
 * @author Luiz Costa
 */
public class Perfil implements GrantedAuthority {


	private static final long serialVersionUID = 1L;
	
	public static final String ADMIN = "ADMIN";
	public static final String COMUM = "COMUM";
	

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	/**
	 * Retorna uma lista de perfis.
	 */
	@Override
	public String getAuthority() {
		return this.nome;
	}
		
}
