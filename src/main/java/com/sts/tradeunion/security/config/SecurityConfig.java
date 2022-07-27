package com.sts.tradeunion.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sts.tradeunion.security.jwt.JWTFilter;
import com.sts.tradeunion.security.jwt.JwtAuthenticationEntryPoint;
import com.sts.tradeunion.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final JWTFilter jwtFilter;
    private final PasswordEncoder passwordEncoder;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final ObjectMapper om = new ObjectMapper();
    public SecurityConfig(UserService userService, JWTFilter jwtFilter, PasswordEncoder passwordEncoder, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userService = userService;
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    /**
     * <h2> Метод конфигурирует Security для таких случаев, как использование нестандартной логин-формы,
     * явное указание уровней доступа к определенным страницам, авторизацию и т.д.<p>
     * Блоки конфигурации разделяются методом {@code and}: <p>
     * @param httpSecurity объект, находящийся в контексте приложения.
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .cors().and().csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.POST,"/auth/login", "/error", "/auth/registration","/swagger-ui/*"
                        ,"/dev.html","/swagger-ui/index.html", "/v3/*", "/v3/api-docs/swagger-config").permitAll()
                .and()
                .authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {

                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    ServletOutputStream out = response.getOutputStream();
                    om.writeValue(out, accessDeniedException.getMessage());
                    out.flush();
                });
    }

    /**
     * <h2> Метод конфигурирует логику аутентификации.
     * @param builder требует объект реализации {@link org.springframework.security.core.userdetails.UserDetailsService}
     *                для классической конфигурации Security или объект реализации {@link org.springframework.security.authentication.AuthenticationProvider}
     *                для кастомной конфигурации Security
     */
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
