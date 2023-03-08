package com.metaenlace.formacion.gestormedico.services;

import com.metaenlace.formacion.gestormedico.dto.UsuarioDTO;
import com.metaenlace.formacion.gestormedico.entities.Usuario;
import com.metaenlace.formacion.gestormedico.mapper.UsuarioMapper;
import com.metaenlace.formacion.gestormedico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<UsuarioDTO> findByUsuario(String usuario) {
        Optional<Usuario> usuarioEntity = usuarioRepository.findByUsuario(usuario);
        if (usuarioEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuarioEntity.get()));
    }
}
