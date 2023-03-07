package com.metaenlace.formacion.gestormedico.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "paciente")
public class Paciente extends Usuario {

	@Column(name = "numSeguridadSocial")
	private String nSS;
	@Column(name = "numTarjeta")
	private String numTarjeta;
	@Column(name = "telefono")
	private String telefono;
	@Column(name = "direccion")
	private String direccion;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
			name = "medicos_paciente",
			joinColumns = { @JoinColumn(name = "paciente_id")},
			inverseJoinColumns = { @JoinColumn(name = "medico_id")}
	)
	private List<Medico> medicos;

	@OneToMany(mappedBy="paciente")
	private List<Cita> citas;


	public Paciente(){
		super();
	}

	public Paciente(String nSS, String numTarjeta, String telefono, String direccion, List<Medico> medicos) {
		super();
		this.nSS = nSS;
		this.numTarjeta = numTarjeta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.medicos = medicos;
	}
}
