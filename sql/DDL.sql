use pruebaCloud;

create table oauth_access_token (
                                    token_id VARCHAR(255),
                                    token BLOB,
                                    authentication_id VARCHAR(255),
                                    user_name VARCHAR(255),
                                    client_id VARCHAR(255),
                                    authentication BLOB,
                                    refresh_token VARCHAR(255)
);

create table oauth_refresh_token (
                                     token_id VARCHAR(255),
                                     token BLOB,
                                     authentication BLOB
);


CREATE TABLE `info_usuario` (
                                `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
                                `username` varchar(500) NOT NULL,
                                `password` varchar(500) NOT NULL,
                                `correo` varchar(1000) NOT NULL,
                                `estado` varchar(45) NOT NULL,
                                `fe_creacion` datetime NOT NULL,
                                PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1 COMMENT='Tabla info de los usuarios'