package org.me.learning.springsecurity1.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.me.learning.springsecurity1.model.Users;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private  String secretKey ="" ;

    public JWTService() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("hmacSha256");
            SecretKey sk = generator.generateKey(); // in Bytes
            secretKey=Base64.getEncoder().encodeToString(sk.getEncoded()); // to string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public  String generateToken (String username){
//        just example
//        return "111111";
        Map<String , Object>  claims = new HashMap<>();
        return Jwts.builder().claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() +60*60*10))  // it is in millisecond
                .and()
                .signWith(etKey())
                .compact();
    }

    private Key etKey() {
//        the key should be converted into bytes
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
