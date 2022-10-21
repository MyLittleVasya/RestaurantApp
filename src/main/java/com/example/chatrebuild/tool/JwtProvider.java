package com.example.chatrebuild.tool;

import com.example.chatrebuild.repo.UserRepo;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtProvider {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public String provideLoginToken(String login) {
        Date expirationDate = Date.from
                (LocalDate
                        .now()
                        .plusDays(1)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
                );
        var user = userRepo.findByUsername(login);
        return Jwts.builder()
                .setExpiration(expirationDate)
                .claim("role", user.getRole().toString().toUpperCase())
                .signWith(SignatureAlgorithm.HS512, login)
                .compact();
    }
    public void validateToken(String token, String login) throws JwtException {
        Jwts.parser().setSigningKey(login).parseClaimsJws(token);
    }

    public String checkRoleJWT(String token, String login) {
        var claims = Jwts.parser().setSigningKey(login).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }


}
