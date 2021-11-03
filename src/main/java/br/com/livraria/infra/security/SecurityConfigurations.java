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

@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	@Autowired
//	private TokenService tokenService;
//	
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(autenticacaoService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//			// libera página de autenticação.
//			//.antMatchers(HttpMethod.POST, "/auth").permitAll()
//			
//			// Somente quem tem perfil de ADMIN pode acessar os recursos de usuários na api.
//			//.antMatchers("/usuarios/**").hasRole("ADMIN")
//			
//			// bloqueia todas outras requisições não autenticadas.
			.anyRequest().authenticated()
//			
//			// gerar o formulário de login.
			.and().formLogin()
			
//			//.and().sessionManagement()
//			//.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			
//			// desativa o Cross-site Request Forgery.
			.and().csrf().disable();
////			.addFilterBefore(new VerificacaoTokenFilter(tokenService, usuarioRepository), 
////							  UsernamePasswordAuthenticationFilter.class);
//			
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// Todos endereços chamados pelo Swagger.
//		web
//			.ignoring()
//			.antMatchers("/v2/api-docs", 
//					"/configuration/ui",
//					"/swagger-resources/**",
//					"/configuration/security",
//					"/swagger-ui.html",
//					"/webjars/**");
//	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
}
