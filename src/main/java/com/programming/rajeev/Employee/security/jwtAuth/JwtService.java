package com.programming.rajeev.Employee.security.jwtAuth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${spring.jwt.SECRET_KEY}")
    private String SECRET_KEY;

    @Value("${spring.jet.EXPIRATION_TIME}")
    private long  EXPIRATION_TIME;


    public String createToken(String userName) {
        Map<String,Object> claims = new HashMap<>();
        return generateToken(userName,claims);
    }


  private String generateToken(String userName, Map<String,Object> claims) {
        System.out.println(EXPIRATION_TIME);
        System.out.println(SECRET_KEY);
        Long time = new Date(System.currentTimeMillis()).getTime();
        System.out.println(time);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }



    public String extractUserName(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String userName)
    {
        return Objects.equals(extractUserName(token), userName) && !isTokenExpired(token);

    }
    public boolean isTokenExpired(String token)
    {
        return extractClaim(token,Claims::getExpiration).before(new Date(System.currentTimeMillis()));
    }



public <T> T extractClaim(String token , Function<Claims, T> claimResolver)
{
    Claims claim = extractAllClaims(token);
    return claimResolver.apply(claim);

}

    public Claims extractAllClaims( String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }




}
