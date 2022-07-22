package com.pessoa.security;

import com.pessoa.model.entiy.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.expiracao}")
    private String expiracao;
    @Value("${jwt.chave-assinatura}")
    private String chaveAssinatura;



    public String geraToken(Usuario usuario){
        SecretKey CHAVE = Keys.hmacShaKeyFor(
                chaveAssinatura.getBytes(StandardCharsets.UTF_8));
        long exp = Long.valueOf(30L);
        LocalDateTime expiracaoToken = LocalDateTime.now().plusMinutes(exp);
        Instant instant = expiracaoToken.atZone(ZoneId.systemDefault()).toInstant();
        Date expiracaoFinal = Date.from(instant);
        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setExpiration(expiracaoFinal)
                .signWith(CHAVE, SignatureAlgorithm.HS512)
                .compact();

    }

//    private Key getSigningKey() {
//        byte[] keyBytes = this.chaveAssinatura.getBytes(StandardCharsets.UTF_8);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    private Claims obtemClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean tokenValido(String token) throws ExpiredJwtException {

        try {
            Claims claims = obtemClaims(token);
            Date expiracaoToken = claims.getExpiration();
            LocalDateTime espirToken = expiracaoToken.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(espirToken);

        } catch(Exception e) {
            System.out.println("Token expirado!");
        }
        return false;
    }

    public String obtemUsuario(String token) throws ExpiredJwtException {
        return obtemClaims(token).getSubject();
    }
}
