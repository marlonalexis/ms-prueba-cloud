package ec.marlonpluas.prueba.cloud.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Entidad AdmiApi
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Data
@Entity
@Table(name = "admi_api")
public class AdmiApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_api")
    private Long idApi;
    @Column(name = "valor")
    private String valor;
    @Column(name = "estado")
    private String estado;

    public static final String idApiValue = "idApi";
}
