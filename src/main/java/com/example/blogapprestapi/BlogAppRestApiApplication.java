package com.example.blogapprestapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppRestApiApplication {

    //explicitly creating bean for a ModelMapper library to tell spring to manage this bean or class and its functionalities
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogAppRestApiApplication.class, args);
    }

}
