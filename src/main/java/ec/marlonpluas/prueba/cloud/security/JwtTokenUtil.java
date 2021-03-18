package ec.marlonpluas.prueba.cloud.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Clase util security token
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Component
public class JwtTokenUtil {
    public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 24;

    @Value("${jwt.secret}")
    private String secretJwt;

    public String getUsernameToken(String token) {
        return getClaimToken(token, Claims::getSubject);
    }

    public String getIdTransaccion(String token) {
        final Claims claims = getAllClaimsToken(token);
        return (String) claims.get("idTransaccion");
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public <T> T getClaimToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsToken(String token) {
        return Jwts.parser().setSigningKey(secretJwt).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateToken(String token) {
        return getClaimToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails, String idTransaccion) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idTransaccion", idTransaccion);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretJwt).compact();
    }
}
