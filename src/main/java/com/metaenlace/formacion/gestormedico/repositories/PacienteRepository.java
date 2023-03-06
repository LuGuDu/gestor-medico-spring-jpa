package com.metaenlace.formacion.gestormedico.repositories;

import com.metaenlace.formacion.gestormedico.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long> {
}
