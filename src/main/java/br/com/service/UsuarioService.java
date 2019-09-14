package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Usuario;
import br.com.repository.UsuarioRepository;

@Service
public class UsuarioService implements IServiceBase<Usuario> {

	@Autowired
	private UsuarioRepository repository;
	
	
	public List<Usuario> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void edit(long id, Usuario object) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	public void salva(Usuario object) {
		this.repository.save(object);
		
	}

	
	public Usuario getByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
