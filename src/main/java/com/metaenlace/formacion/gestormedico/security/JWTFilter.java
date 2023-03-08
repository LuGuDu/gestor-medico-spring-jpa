package com.metaenlace.formacion.gestormedico.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.metaenlace.formacion.gestormedico.services.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter
{
    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    // Filtro para validar las peticiones, que tengan una cabecera con el token JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        String authHeader = request.getHeader("Authorization");
        // comprobar que la cabecera es de autorizacion
        if (authHeader != null)
        {
            // obtener el token jwt
            String jwtToken = authHeader.substring(7);
            // si no hay token retornar error
            if (jwtToken.isBlank())
            {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
            }
            else
            {
                // si el token no es vacio, validarlo
                try
                {
                    // obtener el usuario y validar su contraseña
                    String usuario = jwtUtil.validateTokenAndRetrieveSubject(jwtToken);
                    UserDetails userDetails = usuarioDetailsService.loadUserByUsername(usuario);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            usuario,
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );

                    // si no esta guardado, añadir el token
                    if (SecurityContextHolder.getContext().getAuthentication() == null)
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                }
                catch (JWTVerificationException ex)
                {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
