package com.metaenlace.formacion.gestormedico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "diagnostico")
@Table(name = "diagnosticos")
public class Diagnostico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "valoracion")
	private String valoracionEspecialista;
	@Column(name = "enfermedad")
	private String enfermedad;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cita_id", referencedColumnName = "id")
	private Cita cita;

	public Diagnostico(){}
	
//	public Diagnostico(String valoracionEspecialista, String enfermedad) {
//		super();
//		this.valoracionEspecialista = valoracionEspecialista;
//		this.enfermedad = enfermedad;
//	}

	//@JsonIgnore
	//public Cita getCita(){
//		return this.cita;
//	}


}
