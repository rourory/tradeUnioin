package com.sts.tradeunion;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TradeUnionApplication {


    public static void main(String[] args) {
        SpringApplication.run(TradeUnionApplication.class, args);
    }

    /**
     * Spring bean, используемы для конвертации DTO в Entity
     * @return объект, имеющий методы конвертации pojo-объектов
     */
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    /**
     *     Метод возвращает объект реализации интерфейса {@link PasswordEncoder}, а Spring,
     *     благодаря анннотации {@link Bean} хранит его в контексте.
     *     Используется для шифрования паролей в местах, где это необходимо
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
