package com.metaenlace.formacion.gestormedico.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;

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
