/**
 * 
 */
package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Agendamento;
import br.com.repository.AgendamentosRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Service
public class AgendamentoService implements IServiceBase<Agendamento> {

	@Autowired
	private AgendamentosRepository repository;
	
	@Transactional(readOnly=true)
	public List<Agendamento> lista(){
		return this.repository.findAll();
	}	

	@Transactional
	public Agendamento getByID (long id) {		
		return this.repository.findOne(id);
	}

	@Override
	public void edit(long id, Agendamento object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		this.repository.delete(id);
	}

	@Override
	public void salva(Agendamento agendamento) {
		this.repository.save(agendamento);		
	}

}
