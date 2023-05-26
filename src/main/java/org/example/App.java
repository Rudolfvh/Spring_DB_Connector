package org.example;

import org.example.spring.config.ApplicationConfig;
import org.example.spring.service.CompanyService;
import org.example.spring.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            var userService = context.getBean(UserService.class);
            var companyService = context.getBean(CompanyService.class);
            System.out.println(userService.findById(1L));
            System.out.println(companyService.findById(1L));
        }
    }
}
