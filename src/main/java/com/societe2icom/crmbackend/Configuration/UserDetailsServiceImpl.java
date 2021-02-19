package com.societe2icom.crmbackend.Configuration;

import ch.qos.logback.classic.jul.JULHelper;
import com.societe2icom.crmbackend.Entities.User;
import com.societe2icom.crmbackend.Repository.UserRepository;
import com.sun.javafx.util.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
    private UserRepository UserRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("in load user by email");

 com.societe2icom.crmbackend.Entities.User applicationUser = UserRepository.findByEmail(email);
       /* if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }*/
        return new org.springframework.security.core.userdetails.User(applicationUser.getEmail(),applicationUser.getPassword(),new ArrayList<>());

    }
}