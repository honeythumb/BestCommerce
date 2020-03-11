package application.token;

import application.utilities.comm.TokenConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class TokenProvider {


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(String username, boolean remember) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, remember);
    }

    public String generateToken(String username) {
        return generateToken(username, false);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(TokenConstant.key).parseClaimsJws(token).getBody();
    }

    private String createToken(Map<String, Object> claims, String subject, boolean remember) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis() + (remember ? TokenConstant.remember : TokenConstant.normal))))
                .signWith(SignatureAlgorithm.HS512, TokenConstant.key).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
