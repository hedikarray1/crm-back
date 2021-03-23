package com.societe2icom.crmbackend;

import com.societe2icom.crmbackend.Entities.User;
import com.societe2icom.crmbackend.Repository.UserRepository;
import liquibase.pro.packaged.D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

@SpringBootApplication
@EnableSwagger2

public class CrmbackendApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    public static void main(String[] args) {

        SpringApplication.run(CrmbackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

     //   userRepository.save(new User("2i com","agence 2i com","digitalmark18@gmail.com","123456",new Date(),"20123456","admin","picture.png","token"));

       // userRepository.save(new User("2i com","agence 2i com","hedi.karray@esprit.tn","$2a$10$eEPHHxDp0AWACwQNmSJfuOvJ/FSi.ca8U4dkp7RKWFf4f3ylFemUS",new Date(),"20123456","admin","empty_avatar.png","token"));

    }



}
