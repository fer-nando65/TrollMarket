package com.trollMarket.utility.Implementation;

import com.trollMarket.entity.Account;
import com.trollMarket.utility.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImplementation implements JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Override
    public String generateToken(Account account) {
        return Jwts.builder()
                .subject(account.getUsername())
                .issuer("http://localhost:8085")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .claims()
                .add("username", account.getUsername())
                .add("role", account.getRole())
                .and()
                .signWith(getSecretKey())
                .compact();
    }

    public SecretKey getSecretKey(){
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

    @Override
    public Boolean isValid(String token, UserDetails userDetails) {
        return getClaims(token).get("username").equals(userDetails.getUsername()) &&!isTokenExpired(token);
    }

    public Boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
