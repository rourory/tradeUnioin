package com.sts.tradeunion.security.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sts.tradeunion.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <h2>Класс используется в качестве фильтра входящих запросов.
 * @see JWTUtil
 */
@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserService userService;

    public JWTFilter(JWTUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    /**
     * <h2> Метод используется в качестве фильтра входящих запросов. <p>
     * В данном случае успещной валидации
     * JWT в контекст security приложения помещается {@code authenticationToken} и далее запрос
     * продвигается по цепочке фильтров методом {@code filterChain.doFilter}<p>
     * JWT принято передавать в header запроса под ключем "Authorization" и начинаться с "Bearer "
     * @param request запрос от клиента
     * @param response ответ клменту
     * @param filterChain цепочка фильтров
     * @throws ServletException ошибка в работе сервлета
     * @throws IOException ошибка ввода-вывода
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            try {
                Map<String, String> claims = jwtUtil.validateTokenAndRetrieveClaim(authHeader.substring(7));
                UserDetails userDetails = userService.loadUserByUsername(claims.get("username"));

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (JWTVerificationException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Ошибка в процессе расшифровки JWT");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный JWT");
        }

        filterChain.doFilter(request,response);
    }
}
