package com.openinnovations.securityservice.rest;

import com.openinnovations.securityservice.model.Login;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/login")
public class LoginRest {

    @PostMapping
    String loguearse(@RequestBody Login login) {

        //Consumir un servicio de validación

        return this.getJWTToken(login.getIduser());
    }

    private String getJWTToken(String username) {
        String secretKey = "vg2020SecretKey123";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("bibliotecaVirtualJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes())
                .compact();

        return "vg2020 " + token;
    }
}