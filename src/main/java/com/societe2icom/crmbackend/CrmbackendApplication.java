package com.societe2icom.crmbackend;

import com.societe2icom.crmbackend.Entities.User;
import com.societe2icom.crmbackend.Repository.UserRepository;
import liquibase.pro.packaged.D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class CrmbackendApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    public static void main(String[] args) {

        SpringApplication.run(CrmbackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("2i com","agence 2i com","digitalmark18@gmail.com","123456",new Date(),"20123456","admin","picture.png","token"));
    }
}
