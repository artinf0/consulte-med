package br.com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_pacientes")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	public String nome;
	
	@NotBlank(message = "CPF é obrigatório")
	public String cpf;
	
	@NotBlank(message = "Email é obrigatório")
	public String email;

}
