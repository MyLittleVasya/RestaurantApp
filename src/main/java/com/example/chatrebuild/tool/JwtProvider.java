package com.example.chatrebuild.tool;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

    public String provideToken(String login) {
        Date expirationDate = Date.from
                (LocalDate
                        .now()
                        .plusDays(1)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
                );
        return Jwts.builder()
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, login)
                .compact();
    }

    public void validateToken(String token, String login) throws JwtException {
        Jwts.parser().setSigningKey(login).parseClaimsJws(token);
    }


}
