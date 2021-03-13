package ec.marlonpluas.prueba.cloud.util;

/**
 * Variables constantes del modulo util
 * 
 * @author Marlon Plúas
 * @version 1.0
 * @since 02/03/2020
 */
public abstract class CoreUtilConstants {
	private CoreUtilConstants() {
	}

	public static final String SCHEMA_PRUEBA_CLOUD = "pruebaCloud";
	public static final String TIMEZONE_DATE = "America/Guayaquil";
	public static final String MENSAJE_ERROR = "ERROR";

	// Spring Security

	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT

	public static final String ISSUER_INFO = "https://www.autentia.com/";
	public static final String SUPER_SECRET_KEY = "1234";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
}
