package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.ValidationGrups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 60)
	private String nome;

	@NotBlank
	@Email
	@Size(max = 255)
	private String email;

	@NotBlank
	@Size(max = 20)
	@Column(name = "fone")
	private String telefone;
	
}
