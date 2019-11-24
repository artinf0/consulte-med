package br.com.resources.agendamento;

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

import br.com.model.Agendamento;
import br.com.service.AgendamentoService;

@RestController
@RequestMapping("/api")
public class AgendamentoResources {
	@Autowired
	private AgendamentoService service;

	@PostMapping("/agendamentos")	
	public ResponseEntity<?> add(@RequestBody Agendamento agendamento) {
		this.service.salva(agendamento);
		return ResponseEntity.ok(agendamento);
	}

	@DeleteMapping("/agendamentos/{id}")
	public ResponseEntity<?> remove (@PathVariable(value = "id") Long id) {			
		Agendamento agendamentoRepo = this.service.getByID(id);
		if(Objects.isNull(agendamentoRepo)) {
			this.service.delete(id);		
			return ResponseEntity.ok().body(agendamentoRepo);}
		else
			return ResponseEntity.notFound().build();
	}

	@PutMapping("/agendamentos/{id}")
	public ResponseEntity<?> atualiza(@PathVariable(value = "id") Long id, @Valid @RequestBody Agendamento agendamento){
		Agendamento agendamentoRepo = this.service.getByID(id);

		agendamentoRepo.setId(id);
		agendamentoRepo.setContato(agendamento.getContato());
		agendamentoRepo.setMedico(agendamento.getMedico());
		agendamentoRepo.setPaciente(agendamento.getPaciente());
		agendamentoRepo.setDataAgendamento(agendamento.getDataAgendamento());

		this.service.salva(agendamentoRepo);		
		return ResponseEntity.ok(agendamento);
	}

	@GetMapping("/agendamentos")
	public ResponseEntity<List<Agendamento>> list() {
		List<Agendamento> agendamentos = this.service.lista();
		return !agendamentos.isEmpty() ? ResponseEntity.ok(agendamentos) : ResponseEntity.noContent().build() ;
	}

	@GetMapping("/agendamentos/{id}")
	public ResponseEntity<Agendamento> get(@PathVariable(value = "id") Long id) {
		Agendamento agendamento = this.service.getByID(id);	

		return Objects.isNull(agendamento) ? ResponseEntity.ok(agendamento) : ResponseEntity.notFound().build();
	}
}
