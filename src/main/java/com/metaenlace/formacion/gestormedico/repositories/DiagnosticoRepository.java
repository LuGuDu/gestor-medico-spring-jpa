package com.metaenlace.formacion.gestormedico.repositories;

import com.metaenlace.formacion.gestormedico.entities.Diagnostico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticoRepository extends CrudRepository<Diagnostico, Long> {

}
