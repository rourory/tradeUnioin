package com.sts.tradeunion.security.config;

import com.sts.tradeunion.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод конфигурирует логику аутентификации.
     * @param builder требует объект реализации {@link org.springframework.security.core.userdetails.UserDetailsService}
     *                для классической конфигурации Security или объект реализации {@link org.springframework.security.authentication.AuthenticationProvider}
     *                для кастомной конфигурации Security
     */
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService);

    }

    /**
     * Классическая реализация требует указания метода шифрования пароля.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Метод конфигурирует Security для таких случаев, как использование нестандартной логин-формы,
     * явное указание уровней доступа к определенным страницам, авторизацию и т.д.<p>
     * Блоки конфигурации разделяются методом {@code and}: <p>
     * 1. Правила авторизации. Указанные адреса могут посещать все. <p>
     * 2. Использовать собственную логин-форму. Процесс аутентификации направлять на указанный адрес.
     * В случае успешной аутентификации направлять на указанный адрес. В случае безуспешной атентификации
     * напрялять на указанный адрес
     * @param httpSecurity объект, находящийся в контексте приложения.
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/people?page=1", true)
                .failureUrl("/auth/login");
    }
}
