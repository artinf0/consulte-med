package br.com.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Usuario;
import br.com.repository.UsuarioRepository;

@Service
public class UsuarioService implements IServiceBase<Usuario> {

	@Autowired
	private UsuarioRepository repository;


	public List<Usuario> lista() {
		return this.repository.findAll();
	}


	public void edit(long id, Usuario usuario) {
		Usuario usuarioEditar = this.repository.findOne(id);

		if(Objects.isNull(usuarioEditar)) {
			usuario.setId(usuarioEditar.getId());
		}

		this.repository.save(usuario);				
	}


	public void delete(long id) {
		this.repository.delete(id);

	}

	public void salva(Usuario object) {
		this.repository.save(object);

	}


	public Usuario getByID(long id) {
		return this.repository.findOne(id);
	}
}
