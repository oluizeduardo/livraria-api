package br.com.livraria.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.livraria.model.Perfil;
import br.com.livraria.repository.UsuarioRepository;
import br.com.livraria.service.AutenticacaoService;
import br.com.livraria.service.TokenService;

@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(autenticacaoService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			// libera página de autenticação.
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			
			
			// Somente perfil ADMIN pode cadastrar, apagar ou atualizar um autor.
			.antMatchers(HttpMethod.POST, "/autores/**").hasRole(Perfil.ADMIN)
			.antMatchers(HttpMethod.DELETE, "/autores/**").hasRole(Perfil.ADMIN)
			.antMatchers(HttpMethod.PUT, "/autores/**").hasRole(Perfil.ADMIN)
			
			
			// Somente perfil ADMIN pode cadastrar, apagar ou atualizar um livro.
			.antMatchers(HttpMethod.POST, "/livros/**").hasRole(Perfil.ADMIN)
			.antMatchers(HttpMethod.DELETE, "/livros/**").hasRole(Perfil.ADMIN)
			.antMatchers(HttpMethod.PUT, "/livros/**").hasRole(Perfil.ADMIN)
			
			
			// Somente perfil de ADMIN pode acessar os recursos de usuários.
			.antMatchers("/usuarios/**").hasRole(Perfil.ADMIN)

			
			// bloqueia todas outras requisições não autenticadas.
			.anyRequest().authenticated()
			
			// quando o usuário fizer login, não guarda informações no servidor.
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
			// desativa o Cross-site Request Forgery.
			.and().csrf().disable()
			.addFilterBefore(new VerificacaoTokenFilter(tokenService, usuarioRepository), 
							  UsernamePasswordAuthenticationFilter.class);		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Todos endereços chamados pelo Swagger.
		web
			.ignoring()
			.antMatchers("/v2/api-docs", 
					"/configuration/ui",
					"/swagger-resources/**",
					"/configuration/security",
					"/swagger-ui.html",
					"/webjars/**");
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
}
