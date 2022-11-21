package com.sts.tradeunion.security.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sts.tradeunion.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * <h2>Класс используется в качестве фильтра входящих запросов.
 * @see JWTUtil
 */
@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserServiceImpl userService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public JWTFilter(JWTUtil jwtUtil, UserServiceImpl userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    /**
     * <h2> Метод используется в качестве фильтра входящих запросов. <p>
     * В случае успешной валидации
     * JWT в контекст security приложения помещается {@code authenticationToken} с данными о пользователе, позволяя ему
     * совершать действия согласно своей роли. Генерируется новый токен со сроком действия n-минут от
     * текущего времени и помещается в header {@code response}. Bалее запрос
     * продвигается по цепочке фильтров методом {@code filterChain.doFilter}<p>
     * JWT принято передавать в header запроса под ключем "Authorization" и начинаться с "Bearer "<p>
     * В случае отсутствия header с ключем Authorization в {@code SecurityContextHolder} помещается
     * {@code UsernamePasswordAuthenticationToken} с правами неаутентифицированного пользователся.
     * @param request запрос от клиента
     * @param response ответ клменту
     * @param filterChain цепочка фильтров
     * @throws ServletException ошибка в работе сервлета
     * @throws IOException ошибка ввода-вывода
     * @see JWTUtil
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(Constants.AUTHORIZATION);
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith(Constants.TOKEN_PREFIX)) {
            try {
                Map<String, String> claims = jwtUtil.validateTokenAndRetrieveClaim(authHeader.substring(7));
                UserDetails userDetails = userService.loadUserByUsername(claims.get("username"));

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                response.addHeader(Constants.AUTHORIZATION, Constants.TOKEN_PREFIX + jwtUtil.generateToken(claims.get("username"),claims.get("role")));
                response.addHeader("Access-Control-Expose-Headers", "Authorization");
            } catch (JWTVerificationException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Ошибка в процессе расшифровки JWT");
            }
        } else {
            /* Код в блоке в данном блоке существует для тестирования работы со Swagger.
             Он позволяет создать имитацию авторизованного
             пользователя для доступа к схеме контроллеров. */
                logger.warn("Данные, введенные пользователем, неверны");
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_NON_AUTHORIZED"));
            UserDetails userDetails = new User("api_user", "$2y$12$Y2MfMK7PcAchzL/oaVMk0ecUQEFV.mfwsCHmY6gqn2hpLH8BaZwcy", authorities);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}
