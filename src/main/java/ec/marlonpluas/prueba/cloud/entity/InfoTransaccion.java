package ec.marlonpluas.prueba.cloud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import ec.marlonpluas.prueba.cloud.util.CoreUtilConstants;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Entidad InfoTransaccion
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Data
@Entity
@Table(name = "info_transaccion", schema = CoreUtilConstants.SCHEMA_PRUEBA_CLOUD)
public class InfoTransaccion {
    @Id
    @Column(name = "id_transaccion")
    private String idTransaccion;
    @Column(name = "username")
    private String username;
    @Column(name = "estado")
    private String estado;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = CoreUtilConstants.TIMEZONE_DATE)
    @Column(name = "fe_creacion")
    private Date feCreacion;
    @Column(name = "usr_modificacion")
    private String usrModificacion;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = CoreUtilConstants.TIMEZONE_DATE)
    @Column(name = "fe_modificacion")
    private Date feModificacion;

    public static final String idTransaccionValue = "idTransaccion";
}

