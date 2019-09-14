package br.com.resources.paciente;

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

import br.com.model.Paciente;
import br.com.service.PacienteService;

@RestController
@RequestMapping("/api")
public class PacienteResources {
	@Autowired
	private PacienteService service;

	@PostMapping("/pacientes")	
	public ResponseEntity<?> add(@RequestBody Paciente medico) {
		this.service.salva(medico);
		return ResponseEntity.ok(medico);
	}

	@DeleteMapping("/pacientes/{id}")
	public ResponseEntity<?> remove (@PathVariable(value = "id") Long id) {			
		Paciente pacientesRepo = this.service.getByID(id);
		if(Objects.isNull(pacientesRepo)) {
			this.service.delete(id);		
			return ResponseEntity.ok().body(pacientesRepo);}
		else
			return ResponseEntity.notFound().build();
	}

	@PutMapping("/pacientes/{id}")
	public ResponseEntity<?> atualiza(@PathVariable(value = "id") Long id, @Valid @RequestBody Paciente paciente){
		Paciente pacienteRepo = this.service.getByID(id);	
		
		pacienteRepo.setId(id);
		pacienteRepo.setCpf(paciente.getCpf());
		pacienteRepo.setEmail(paciente.getEmail());
		pacienteRepo.setNome(paciente.getNome());
		
		this.service.salva(pacienteRepo);

		return ResponseEntity.ok(paciente);

	}

	@GetMapping("/pacientes")
	public ResponseEntity<List<Paciente>> list() {
		List<Paciente> pacientes = this.service.lista();
		return !pacientes.isEmpty() ? ResponseEntity.ok(pacientes) : ResponseEntity.noContent().build() ;
	}

	@GetMapping("/pacientes/{id}")
	public ResponseEntity<Paciente> get(@PathVariable(value = "id") Long id) {
		Paciente paciente = this.service.getByID(id);	

		return Objects.isNull(paciente) ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
	}
}
