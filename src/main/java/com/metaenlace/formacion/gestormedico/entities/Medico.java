package com.metaenlace.formacion.gestormedico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Medico extends Usuario{

	@Column(name = "numColegiado")
	private String numColegiado;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "medicos_paciente",
			joinColumns = { @JoinColumn(name = "medico_id")},
			inverseJoinColumns = { @JoinColumn(name = "paciente_id")}
	)
	private List<Paciente> pacientes;

	@OneToMany(mappedBy="medico")
	private List<Cita> citas;

	public Medico(){
		super();
	}

	public Medico(String numColegiado) {
		super();
		this.numColegiado = numColegiado;
	}
	
}
