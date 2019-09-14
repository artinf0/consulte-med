package br.com.resources.medico;

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

import br.com.model.Medico;
import br.com.service.MedicoService;

@RestController
@RequestMapping("/api")
public class MedicoResources {

	@Autowired
	private MedicoService service;

	@PostMapping("/medicos")	
	public ResponseEntity<?> add(@RequestBody Medico medico) {
		this.service.salva(medico);
		return ResponseEntity.ok(medico);
	}

	@DeleteMapping("/medicos/{id}")
	public ResponseEntity<?> remove (@PathVariable(value = "id") Long id) {			
		Medico medicoRepo = this.service.getMedicoByID(id);
		if(Objects.isNull(medicoRepo)) {
			this.service.delete(id);		
			return ResponseEntity.ok().body(medicoRepo);}
		else
			return ResponseEntity.notFound().build();
	}

	@PutMapping("/medicos/{id}")
	public ResponseEntity<?> atualiza(@PathVariable(value = "id") Long id, @Valid @RequestBody Medico medico){
		Medico medicoRepo = this.service.getMedicoByID(id);

		medicoRepo.setCrm(medico.getCrm());
		medicoRepo.setEmail(medico.getEmail());
		medicoRepo.setId(id);
		medicoRepo.setNome(medico.getNome());

		this.service.salva(medicoRepo);

		return ResponseEntity.ok(medico);

	}

	@GetMapping("/medicos")
	public ResponseEntity<List<Medico>> list() {
		List<Medico> medicos = this.service.lista();
		return !medicos.isEmpty() ? ResponseEntity.ok(medicos) : ResponseEntity.noContent().build() ;
	}

	@GetMapping("/medicos/{id}")
	public ResponseEntity<Medico> get(@PathVariable(value = "id") Long id) {
		Medico medico = this.service.getMedicoByID(id);	

		return Objects.isNull(medico) ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
	}
}
