package org.example;

import org.example.spring.service.CompanyService;
import org.example.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App 
{
    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
            var userService = context.getBean(UserService.class);
            var companyService = context.getBean(CompanyService.class);
            userService.findById(1L).ifPresent(System.out::println);
            companyService.findById(1L).ifPresent(System.out::println);
        }
    }
}
