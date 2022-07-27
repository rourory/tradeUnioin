package com.sts.tradeunion;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
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

}
