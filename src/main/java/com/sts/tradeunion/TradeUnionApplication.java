package com.sts.tradeunion;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
