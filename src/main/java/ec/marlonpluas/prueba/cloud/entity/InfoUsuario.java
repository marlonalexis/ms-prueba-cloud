package ec.marlonpluas.prueba.cloud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import ec.marlonpluas.prueba.cloud.util.CoreUtilConstants;
import lombok.Data;

/**
 * Entidad InfoUsuario
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Data
@Entity
@Table(name = "info_usuario")
public class InfoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "correo")
    private String correo;
    @Column(name = "estado")
    private String estado;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = CoreUtilConstants.TIMEZONE_DATE)
    @Column(name = "fe_creacion")
    private Date feCreacion;

    public static final String idUsuarioValue = "idUsuario";
}
