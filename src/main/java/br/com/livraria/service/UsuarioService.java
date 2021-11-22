package br.com.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.livraria.dto.AtualizacaoUsuarioFormDTO;
import br.com.livraria.dto.UsuarioDTO;
import br.com.livraria.dto.UsuarioFormDTO;
import br.com.livraria.infra.EnviadorDeEmail;
import br.com.livraria.model.Perfil;
import br.com.livraria.model.Usuario;
import br.com.livraria.repository.PerfilRepository;
import br.com.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
	private PerfilRepository perfilRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EnviadorDeEmail enviadorDeEmail;

	
	
	public Page<UsuarioDTO> listar(Pageable paginacao) 
	{
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(t -> modelMapper.map(t, UsuarioDTO.class));
	}

	@Transactional
	public UsuarioDTO cadastrar(UsuarioFormDTO dto) 
	{
		/*
		 * Linha removida pois o modelMapper estava definindo o id
		 * do perfil como id do usuário, fazendo sobrescrever os usuários 
		 * já cadastrados no banco.
		 */
//		Usuario usuario = modelMapper.map(dto, Usuario.class);
		
		// Linha adicionada para resolver o problema acima.
		Usuario usuario = new Usuario(dto.getNome(), dto.getLogin());
		
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		usuario.addPerfil(perfil);
		usuario.setSenha();
		usuario.setEmail(dto.getEmail());
		
		Usuario usuarioCadastrado = usuarioRepository.save(usuario);
		
		if (usuarioCadastrado != null) 
		{
			enviarEmailAoNovoUsuario(usuario);
		}
		
		return modelMapper.map(usuario, UsuarioDTO.class);
	}


	private void enviarEmailAoNovoUsuario(Usuario usuario) {
		String destinatario = usuario.getEmail();
		String assunto = "Bem vindo à Livraria Digital!";

		// No futuro pesquisar sobre: spring boot thymeleaf email
		String mensagem = String.format(
				"Olá, %s!\n\n" 
			+ "Segue abaixo seus dados de acesso ao sistema:\n\n"			
			+ "\tLogin: %s\n" + "\tSenha: %s\n\n" + "Att.\n" + "Equipe de suporte.\n\n",
						usuario.getNome(), usuario.getLogin(), usuario.getSenha());

		enviadorDeEmail.enviarEmail(destinatario, assunto, mensagem);
	}

	@Transactional
	public UsuarioDTO atualizar(@Valid AtualizacaoUsuarioFormDTO dto) 
	{
		Usuario usuario = usuarioRepository.getById(dto.getId());		
		usuario.atualizarInformacoes(dto.getNome(), dto.getLogin());
		// Nesse momento a JPA percebe que uma entidade foi carregada do banco de dados
		// e teve seus dados modificados. A atualização é feita de forma automática pelo JPA.
		
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Transactional
	public void remover(Integer id) 
	{
		usuarioRepository.deleteById(id);
	}

	public UsuarioDTO detalhar(Integer id) 
	{
		Usuario usuario = usuarioRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());

		return modelMapper.map(usuario, UsuarioDTO.class);
	}

}
