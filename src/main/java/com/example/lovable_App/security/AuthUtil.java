package com.example.lovable_App.security;
import com.example.lovable_App.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;


@Component
public class AuthUtil {


    @Value("${jwt.secret-key}")
    private  String jwtSecretKey;

    public SecretKey getJwtSecretKey() {
        return  Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    public String generateAccessToken(User user) {
        return Jwts.builder().subject(user.getUsername())
                .claim("userId",user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*10))
                .signWith(getJwtSecretKey())
                .compact();
    }


    public JwtUserPrinciple verifyAccessToken(String token) //get userId from token
     {
         Claims claims = Jwts.parser().verifyWith(getJwtSecretKey()).build()
             .parseSignedClaims(token).getBody();

         Long userId=Long.parseLong(claims.get("userId",String.class));
         String userName=claims.getSubject();

         return new JwtUserPrinciple(userId,userName,new ArrayList<>());

     }

     //Getting current userId
     public Long getCurrentUserId(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication==null||!(authentication.getPrincipal() instanceof JwtUserPrinciple userPrincipal)){
        throw new AuthenticationCredentialsNotFoundException("No JWT Found");

    }
         return userPrincipal.userId();
     }
}
