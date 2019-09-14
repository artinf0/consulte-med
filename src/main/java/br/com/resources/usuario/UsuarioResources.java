package br.com.resources.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Usuario;
import br.com.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioResources {

	@Autowired
	private UsuarioService service;
	

	@PostMapping("/usuarios")	
	public ResponseEntity<?> add(@RequestBody Usuario usuario) {
		this.service.salva(usuario);
		return ResponseEntity.ok(usuario);
	}
	
}
