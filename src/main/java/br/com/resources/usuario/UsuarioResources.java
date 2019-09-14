package br.com.resources.usuario;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> remove (@PathVariable(value = "id") Long id) {			
		Usuario usuarioRepo = this.service.getByID(id);
		if(Objects.isNull(usuarioRepo)) {
			this.service.delete(id);		
			return ResponseEntity.ok().body(usuarioRepo);}
		else
			return ResponseEntity.notFound().build();
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> atualiza(@PathVariable(value = "id") Long id, @Valid @RequestBody Usuario usuario){
		Usuario usuarioRepo = this.service.getByID(id);	

		usuarioRepo.setId(id);
		usuarioRepo.setPrimeiroNome(usuario.getPrimeiroNome());
		usuarioRepo.setSegundoNome(usuario.getSegundoNome());
		usuarioRepo.setEmail(usuario.getEmail());

		this.service.salva(usuarioRepo);

		return ResponseEntity.ok(usuario);

	}

	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> list() {
		List<Usuario> usuarios = this.service.lista();
		return !usuarios.isEmpty() ? ResponseEntity.ok(usuarios) : ResponseEntity.noContent().build() ;
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> get(@PathVariable(value = "id") Long id) {
		Usuario usuario = this.service.getByID(id);	

		return Objects.isNull(usuario) ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
}