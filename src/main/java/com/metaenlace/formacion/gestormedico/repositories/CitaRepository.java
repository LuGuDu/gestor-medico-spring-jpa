package com.metaenlace.formacion.gestormedico.repositories;

import com.metaenlace.formacion.gestormedico.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    //ejemplo: List<User> findByEmailAddressAndLastname(String emailAddress, String lastname);
    //doc: https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html

}
