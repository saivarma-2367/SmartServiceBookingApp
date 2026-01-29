package com.smartservice.smart_service_platform;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        String secret = Encoders.BASE64.encode(
                Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256)
                        .getEncoded()
        );
        System.out.println(secret);
    }
}
