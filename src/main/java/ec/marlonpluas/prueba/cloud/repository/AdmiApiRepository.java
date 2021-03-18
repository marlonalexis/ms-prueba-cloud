package ec.marlonpluas.prueba.cloud.repository;

import ec.marlonpluas.prueba.cloud.entity.AdmiApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio AdmiApi
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Repository
public interface AdmiApiRepository extends JpaRepository<AdmiApi, Long> {
    boolean existsByValor(String valor);

    Optional<AdmiApi> findByValor(String valor);
}
