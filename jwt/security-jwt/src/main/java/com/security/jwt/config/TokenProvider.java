package com.security.jwt.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.*;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;


@Slf4j
@Configuration
public class TokenProvider {

    @Value("${jwt.private.key}")
    RSAPrivateKey privateKey;

    @Value("${jwt.public.key}")
    RSAPublicKey publicKey;

    private Instant now = Instant.now();
    private long expiry = 150L; // 3분
    private long refreshExpiry = 36000L;   // 2시간

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    public String createAccessToken(String scope, String email) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(email)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .claim("scope", scope)
                .build();

        return jwtEncoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
