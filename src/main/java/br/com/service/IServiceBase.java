package br.com.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface IServiceBase<T> {
	
	@Transactional
	public List<T> lista();
	
	@Transactional
	public void edit(long id, T object);

	@Transactional
	public void delete(long id);
	
	
	public void salva(T object);
	
	@Transactional
	public T getByID (long id);
}
