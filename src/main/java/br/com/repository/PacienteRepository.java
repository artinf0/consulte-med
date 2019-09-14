package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>  {

}
