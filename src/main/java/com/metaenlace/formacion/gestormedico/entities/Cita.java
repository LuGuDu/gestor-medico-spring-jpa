package com.metaenlace.formacion.gestormedico.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "cita")
@Table(name = "citas")
public class Cita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "fechaHora")
	private Date fechaHora;
	@Column(name = "motivo")
	private String motivoCita;

	@ManyToOne
	@JoinColumn(name="paciente_id")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name="medico_id")
	private Medico medico;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "diagnostico_id", referencedColumnName = "id")
	private Diagnostico diagnostico;

	public Cita(){}
	
	public Cita(Date fechaHora, String motivoCita) {
		super();
		this.fechaHora = fechaHora;
		this.motivoCita = motivoCita;
	}

}
