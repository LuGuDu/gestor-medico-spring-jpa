package com.metaenlace.formacion.gestormedico.services;

import com.metaenlace.formacion.gestormedico.entities.Usuario;
import com.metaenlace.formacion.gestormedico.repositories.MedicoRepository;
import com.metaenlace.formacion.gestormedico.repositories.PacienteRepository;
import com.metaenlace.formacion.gestormedico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

// implementacion customizada para obtener los detalles del usuario que se va a autorizar
@Component
public class UsuarioDetailsService implements UserDetailsService
{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        Optional<Usuario> usuarioRes = usuarioRepository.findByUsuario(username);
        String rol = "";
        if (usuarioRes.isEmpty())
            throw new UsernameNotFoundException("Usuario not found : " + username);

        if (medicoRepository.findByUsuario(username).isPresent()){
            rol = "ROLE_MEDICO";
        } else {
            rol = "ROLE_PACIENTE";

        }

        Usuario usuario = usuarioRes.get();

        return new User(
                username,
                usuario.getClave(),
                Collections.singletonList(new SimpleGrantedAuthority(rol))//esto hay que cambiarlo
        );

    }
}
