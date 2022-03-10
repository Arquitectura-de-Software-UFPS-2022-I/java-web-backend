package com.arquitectura.firmas.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.arquitectura.firmas.auth.filter.SimpleGrantedAuthorityMixin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Component
public class JWTServiceImpl implements  JWTService{
    private Logger logger = LoggerFactory.getLogger(JWTServiceImpl.class);

    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static final long EXPIRATION_DATE = 14000000L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @Override
    public String create(Authentication auth) throws IOException {

        String email = ((User) auth.getPrincipal()).getUsername();

        logger.info("EMAIL DEVUELTO EN JWT : "+ email);

        Collection<? extends GrantedAuthority> rol = auth.getAuthorities();

        Claims claims = Jwts.claims();
        logger.info("ROL DEVUELTO EN JWT : " + new ObjectMapper().writeValueAsString(rol) );
        claims.put("authorities", new ObjectMapper().writeValueAsString(rol));

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .compact();

        return token;
    }

    @Override
    public boolean validate(String token) {

        try {

            getClaims(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

    @Override
    public Claims getClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(resolve(token)).getBody();
        return claims;
    }

    @Override
    public String getUsername(String token) {
        // TODO Auto-generated method stub
        return getClaims(token).getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
        Object rol = getClaims(token).get("authorities");

        Collection<? extends GrantedAuthority> authorities = Arrays
                .asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                        .readValue(rol.toString().getBytes(), SimpleGrantedAuthority[].class));

        return authorities;
    }

    @Override
    public String resolve(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }
}
