package br.com.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Paciente;
import br.com.repository.PacienteRepository;

@Service
public class PacienteService implements IServiceBase<Paciente>{
	@Autowired
	private PacienteRepository repository;

	@Transactional(readOnly=true)
	public List<Paciente> lista(){
		return this.repository.findAll();
	}

	@Transactional
	public void salva(Paciente paciente) {
		this.repository.save(paciente);
	}

	@Transactional
	public void delete(long id) {
		this.repository.delete(id);		
	}

	@Transactional
	public void edit(long id, Paciente paciente) {	
		Paciente pacienteEditar = this.repository.findOne(id);

		if(Objects.isNull(pacienteEditar)) {
			paciente.setId(pacienteEditar.getId());
		}

		this.repository.save(paciente);		
	}

	@Transactional
	public Paciente getByID (long id) {		
		return this.repository.findOne(id);
	}

}