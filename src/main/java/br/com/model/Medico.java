package br.com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_medicos")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotEmpty(message = "{email.vazio}")
	@Email
	private String email;
	
	@NotBlank(message = "CRM é obrigatório")
	private String crm;
}
