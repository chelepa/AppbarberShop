package br.com.barberShop.component;

import br.com.barberShop.constants.ConstantesAutentication;
import br.com.barberShop.dto.customer.CustomerDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;

@Component
public class JwtTokenUtilComponent implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${spring.jwtSecret}")
    private String secret;

    public String generateToken(CustomerDetails customerDetails) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        return Jwts.builder()
                .setSubject(customerDetails.getLogin())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .claim(ConstantesAutentication.FULL_NAME.getMessage(), customerDetails.getUsername())
                .claim(ConstantesAutentication.ROLES_CLAIM.getMessage(), AuthorityListToCommaSeparatedString(customerDetails.getAuthorities()).replaceAll(ConstantesAutentication.ROLE.getMessage(), ""))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, apiKeySecretBytes)
                .compact();
    }

    private static String AuthorityListToCommaSeparatedString(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesAsSetOfString = AuthorityUtils.authorityListToSet(authorities);
        return StringUtils.join(authoritiesAsSetOfString, ", ");
    }

    public String getLoginFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, CustomerDetails customerDetails) {
        final String login = getLoginFromToken(token);
        return (login.equals(customerDetails.getLogin()) && !isTokenExpired(token));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
