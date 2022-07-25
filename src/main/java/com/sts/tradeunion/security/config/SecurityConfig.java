package com.sts.tradeunion.security.config;

import com.sts.tradeunion.security.jwt.JWTFilter;
import com.sts.tradeunion.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final JWTFilter jwtFilter;
    public SecurityConfig(UserService userService, JWTFilter jwtFilter) {
        this.userService = userService;
        this.jwtFilter = jwtFilter;
    }

    /**
     * <h2> Метод конфигурирует Security для таких случаев, как использование нестандартной логин-формы,
     * явное указание уровней доступа к определенным страницам, авторизацию и т.д.<p>
     * Блоки конфигурации разделяются методом {@code and}: <p>
     * 1. Отключение защиты от межсайтовой подделки запросов <br>
     * 2. Правила авторизации. Указанные адреса могут посещать все. <br>
     * 3. Использовать собственную логин-форму. Процесс аутентификации направлять на указанный адрес.
     * В случае успешной аутентификации направлять на указанный адрес. В случае безуспешной атентификации
     * напрялять на указанный адрес. <br>
     * 4. Конфигурация logout. Логика реализуется самим Spring. <br>
     * 5. Использование stateless-подхода к аутентификации <p>
     *    Далее указывается используемый фильтр.
     * @param httpSecurity объект, находящийся в контексте приложения.
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login", "/error", "/auth/registration").permitAll()
                .anyRequest().hasAnyRole("USER","ADMIN")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/people?page=1", true)
                .failureUrl("/auth/login")
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * <h2> Метод конфигурирует логику аутентификации.
     * @param builder требует объект реализации {@link org.springframework.security.core.userdetails.UserDetailsService}
     *                для классической конфигурации Security или объект реализации {@link org.springframework.security.authentication.AuthenticationProvider}
     *                для кастомной конфигурации Security
     */
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService)
                .passwordEncoder(getPasswordEncoder());

    }

     /**
     * Классическая реализация требует указания метода шифрования пароля. <p>
      *     Метод возвращает объект реализации интерфейса {@link PasswordEncoder}, а Spring, <p>
      *     благодаря анннотации {@link Bean} хранит его в контексте.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
