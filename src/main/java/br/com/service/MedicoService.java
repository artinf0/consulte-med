package br.com.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Medico;
import br.com.repository.MedicoRepository;

@Service
public class MedicoService {
	@Autowired
	private MedicoRepository repository;

	@Transactional(readOnly=true)
	public List<Medico> lista(){
		return this.repository.findAll();
	}

	@Transactional
	public void salva(Medico medico) {
		this.repository.save(medico);
	}

	@Transactional
	public void delete(long id) {
		this.repository.delete(id);		
	}

	@Transactional
	public void edit(long id, Medico medico) {	
		Medico medicoEditar = this.repository.findOne(id);

		if(Objects.isNull(medicoEditar)) {
			medico.setId(medicoEditar.getId());
		}

		this.repository.save(medico);		
	}

	@Transactional
	public Medico getMedicoByID (long id) {		
		return this.repository.findOne(id);
	}

}
