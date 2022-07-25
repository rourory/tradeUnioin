package com.sts.tradeunion.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Класс отвечает за генерацию и валидацию JWT.
 */
@Component
public class JWTUtil {

    /**
     * Строковое поле, хранящее информацию об уникальном ключе во внешнем файле.<p>
     * Секретный ключ необходим для валидации приходящих от клиента токенов. Сам ключ входит в состав
     * Signature токена и является гарантом защиты токена от подделывания данных, содержащихся в Payload токена.
     */
    @Value("${secretKey}")
    private String secretKey;

    @Value("${issuer}")
    private String issuer;

    /**
     * Метод отвечает за генерацию JWT. В данном случает определяется токен со следующими параметрами: <p>
     * 1. Subject - информация о том, что в целом содержит токен. <p>
     * 2-3. Claim - пара ключ-значение, являющаяся элементом Payload токена. <p>
     * 4. IssuedAt - время выдачи токена. <p>
     * 5. Issuer - подпись субъекта, выдаваемого токен. <p>
     * 6. ExpiresAt - время действия токена. <p>
     * 7. Sign - подпись токена секретным ключем. <p>
     *
     * @param username - значение, отражающее имя пользователя. Предназначено для помещения в Payload токена
     * @param role     - значение, отражающее роль пользователя в системе. Предназначено для помещения в Payload токена
     * @return JWT токен в виде строки.
     */
    public String generateToken(String username, String role) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withIssuer(issuer)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * Метод отвечает за валидацию JWT.
     * @param token - JWT в виде строки.
     * @return HashMap со строками, содержащимися в Payload токена.
     * @throws JWTVerificationException - ошибка при верификации токена.
     */
    public Map<String,String> validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = buildVerifier();
        DecodedJWT jwt = verifier.verify(token);
        Map<String,String> claims = new HashMap<>();
        claims.put("username", jwt.getClaim("username").asString());
        claims.put("role", jwt.getClaim("role").asString());
        return claims;
    }


    private JWTVerifier buildVerifier() {
        return JWT.require(Algorithm.HMAC256(secretKey))
                .withSubject("User details")
                .withIssuer(issuer)
                .build();
    }
}
