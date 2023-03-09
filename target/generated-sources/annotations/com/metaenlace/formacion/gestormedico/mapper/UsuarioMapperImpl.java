package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.UsuarioDTO;
import com.metaenlace.formacion.gestormedico.entities.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-08T11:30:47+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setUsuario( usuario.getUsuario() );
        usuarioDTO.setNombre( usuario.getNombre() );
        usuarioDTO.setApellidos( usuario.getApellidos() );
        usuarioDTO.setClave( usuario.getClave() );

        return usuarioDTO;
    }
}
