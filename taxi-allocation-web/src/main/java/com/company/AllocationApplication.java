package com.company;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableWebMvc
@ComponentScan(basePackages = {"com.company"})
public class AllocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllocationApplication.class);
    }


    @Bean
    public Gson createCustomGsonBean(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.disableHtmlEscaping().create();
    }

    @Bean
    public RestTemplate createRestTemplateBean(){
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter= new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        restTemplate.getMessageConverters().addAll(Arrays.asList(new StringHttpMessageConverter(StandardCharsets.UTF_8), mappingJackson2HttpMessageConverter));
        return restTemplate;
    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory(){
//        EntityManagerFactory entityManagerFactory = new E
//    }
}
