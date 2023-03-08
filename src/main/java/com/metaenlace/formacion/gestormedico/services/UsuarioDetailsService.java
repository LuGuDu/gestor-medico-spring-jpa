package com.metaenlace.formacion.gestormedico.services;

import com.metaenlace.formacion.gestormedico.entities.Usuario;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        System.out.println("HOLA MUNDO");

        Optional<Usuario> usuarioRes = usuarioRepository.findByUsuario(username);

        if (usuarioRes.isEmpty())
            throw new UsernameNotFoundException("Usuario not found : " + username);

        Usuario usuario = usuarioRes.get();


        return new User(
                username,
                usuario.getClave(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEDICO"))//esto hay que cambiarlo
        );

    }
}
