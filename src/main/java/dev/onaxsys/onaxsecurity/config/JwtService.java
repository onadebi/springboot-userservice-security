package dev.onaxsys.onaxsecurity.config;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import dev.onaxsys.onaxsecurity.user.UserProfile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.Map;

@Service
public class JwtService {

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a token with user profile without specifiying any claims
     * @param userProfile details of the user
     * @return String
     */
    public String generateToken(UserProfile userProfile){
        return generateToken(new HashMap<>(), userProfile);
    }

    /**
     * Generates a token with user profile and extra claims specified
     * @param extraClaims
     * @param userProfile details of the user
     * @return String
     */
    public String generateToken(Map<String, Object> extraClaims, UserProfile userProfile) {
        return Jwts.builder().claims(extraClaims).subject(userProfile.getUsername()).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSingningKey()).compact();
    }

    public boolean ValidateToken(String token, UserProfile userProfile) {
        final String username = extractUsername(token);
        return (username.equals(userProfile.getUsername()) && !isTokenExpired(token));
    }

    

    private Claims extractAllClaims(String token) {
        Jws<Claims> parserBuilder = Jwts.parser().verifyWith(getSingningKey()).build().parseSignedClaims(token);
        Claims body = parserBuilder.getPayload();
        return body;
        // #region OBSOLETE
        // SecretKey secretKey = Keys.hmacShaKeyFor(token.getBytes());

        // JwtParser jwtParser =
        // Jwts.parser().verifyWith(secretKey).build();//.parse(token);
        // Jwt<?, ?> item = jwtParser.parse(token);

        // Claims body = item.getPayload();

        // Jws<Claims> jws = parserBuilder.parseClaimsJws(token);
        // // Jwt<UnprotectedHeader, Claims> jwt =
        // Jwts.parser().setSigningKey(secretKey).build().parseClaimsJwt(token);
        // Jws<Claims> jws =
        // Jwts.parser().verifyWith(secretKey);//.parseClaimsJws(token);

        // Jwt<UnprotectedHeader, Claims> jwt =
        // Jwts.parser().setSigningKey(secretKey).build().parseClaimsJwt(token);

        // Claims body = jws.getBody();
        // return
        // Jwts.parser().setSigningKey("secret").build().parseClaimsJws(token).getBody();

        // #endregion
    }

    // #region HELPER METHODS
    private SecretKey getSingningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Appsettings.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
    // #endregion
}
