package ec.marlonpluas.prueba.cloud.security.service;

import java.util.ArrayList;

import ec.marlonpluas.prueba.cloud.entity.InfoUsuario;
import ec.marlonpluas.prueba.cloud.repository.InfoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service JwtUserDetailsService
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private InfoUsuarioRepository infoUsuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        InfoUsuario user = infoUsuarioRepo.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
