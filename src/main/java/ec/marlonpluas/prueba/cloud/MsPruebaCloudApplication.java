package ec.marlonpluas.prueba.cloud;

import ec.marlonpluas.prueba.cloud.entity.InfoUsuario;
import ec.marlonpluas.prueba.cloud.enums.Estado;
import ec.marlonpluas.prueba.cloud.repository.InfoUsuarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
@ComponentScan({ "ec.marlonpluas" })
public class MsPruebaCloudApplication implements CommandLineRunner {
    Logger log = LogManager.getLogger(this.getClass());

    @Value("${correo.admin}")
    private String correoAdmin;

    @Value("${user.admin}")
    private String userAdmin;

    @Value("${pass.admin}")
    private String passAdmin;

    @Autowired
    private BCryptPasswordEncoder encodePass;

    @Autowired
    private InfoUsuarioRepository infoUsuarioRepo;

    public static void main(String[] args) {
        SpringApplication.run(MsPruebaCloudApplication.class, args);
    }

    @Override
    public void run(String... args) {
        /* Creacion de usuario administrador */
        if (!infoUsuarioRepo.existsByUsernameIgnoreCase(userAdmin)) {
            log.info("Creando usuario administrador {}", userAdmin);
            InfoUsuario admin = new InfoUsuario();
            admin.setUsername(userAdmin);
            admin.setPassword(encodePass.encode(passAdmin));
            admin.setCorreo(correoAdmin);
            admin.setEstado(Estado.Activo.toString());
            admin.setFeCreacion(new Date());
            infoUsuarioRepo.save(admin);
        }
    }
}
