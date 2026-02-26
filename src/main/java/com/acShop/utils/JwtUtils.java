package com.acShop.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static SecretKey signKey =
            Keys.hmacShaKeyFor("acShopacShopacShopacShopacShop12".getBytes(StandardCharsets.UTF_8));
    private static Long expireTime = 3600_000L;

    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .claims(claims)
                .signWith(signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .compact();
        return jwt;
    }
    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .verifyWith(signKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return claims;
    }
}



