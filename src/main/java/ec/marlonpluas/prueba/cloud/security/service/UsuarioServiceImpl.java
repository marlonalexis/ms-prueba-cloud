package ec.marlonpluas.prueba.cloud.security.service;

import java.util.ArrayList;
import java.util.List;

import ec.marlonpluas.prueba.cloud.entity.InfoUsuario;
import ec.marlonpluas.prueba.cloud.enums.Estado;
import ec.marlonpluas.prueba.cloud.repository.InfoUsuarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;


/**
 * Clase UsuarioServiceImpl, creacion de usuario de session
 *
 * @author Marlon Pluas
 * @version 1.0.0
 */
@Service
public class UsuarioServiceImpl implements UserDetailsService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    InfoUsuarioRepository infoUsuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        InfoUsuario usuario = infoUsuarioRepo.findOneByUsername(username);
        if (usuario == null || usuario.getEstado().equalsIgnoreCase(Estado.Inactivo.toString())) {
            log.error("Usuario {} no existe o no se encuentra activo", username);
            throw new OAuth2Exception("Usuario no existe o no se encuentra activo");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
