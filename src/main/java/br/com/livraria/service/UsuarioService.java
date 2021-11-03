package br.com.livraria.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.livraria.dto.UsuarioDTO;
import br.com.livraria.dto.UsuarioFormDTO;
import br.com.livraria.model.Usuario;
import br.com.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
//	@Autowired
//	private PerfilRepository perfilRepository; 
	
	@Autowired
	private ModelMapper modelMapper;

	
	
	public Page<UsuarioDTO> listar(Pageable paginacao) 
	{
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(t -> modelMapper.map(t, UsuarioDTO.class));
	}

	public UsuarioDTO cadastrar(UsuarioFormDTO dto) 
	{
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		
//		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
//		usuario.addPerfil(perfil);
		usuario.setSenha();
		
		usuarioRepository.save(usuario);
		
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

//	@Transactional
//	public UsuarioDTO atualizar(@Valid AtualizacaoUsuarioFormDTO dto) {
//		Usuario usuario = usuarioRepository.getById(dto.getId());		
//		usuario.atualizarInformacoes(dto.getNome(), dto.getLogin());
//		// Nesse momento a JPA percebe que uma entidade foi carregada do banco de dados
//		// e teve seus dados modificados. A atualização é feita de forma automática pelo JPA.
//		
//		// Mapeia o retorno para um TransacaoDTO.
//		return modelMapper.map(usuario, UsuarioDTO.class);
//	}

//	@Transactional
//	public void remover(Integer id) 
//	{
//		usuarioRepository.deleteById(id);
//	}
//
	public UsuarioDTO detalhar(Integer id) 
	{
		Usuario usuario = usuarioRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		// Mapeia o retorno para um TransacaoDTO.
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

}
