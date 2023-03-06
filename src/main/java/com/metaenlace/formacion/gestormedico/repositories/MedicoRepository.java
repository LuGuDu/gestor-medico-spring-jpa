package com.metaenlace.formacion.gestormedico.repositories;

import com.metaenlace.formacion.gestormedico.entities.Medico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends CrudRepository<Medico, Long> {

}
