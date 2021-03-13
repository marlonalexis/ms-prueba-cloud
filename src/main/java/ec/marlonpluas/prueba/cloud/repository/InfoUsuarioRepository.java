package ec.marlonpluas.prueba.cloud.repository;

import ec.marlonpluas.prueba.cloud.entity.InfoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio InfoUsuario
 *
 * @author Marlon Pluas
 * @version 1.0.0
 */
@Repository
public interface InfoUsuarioRepository extends JpaRepository<InfoUsuario, Long> {
    boolean existsByUsernameIgnoreCase(String username);
    InfoUsuario findOneByUsername(String username);
}
