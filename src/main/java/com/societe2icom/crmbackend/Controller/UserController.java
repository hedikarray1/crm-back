package com.societe2icom.crmbackend.Controller;


import com.societe2icom.crmbackend.Entities.User;
import com.societe2icom.crmbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("get_all")
    public List<User> getAll(){
       return userRepository.findAll();
    }

    @GetMapping("get_by_role/{role}")
    public List<User>getClients(@PathVariable("role")String role){
        return userRepository.findByRole(role);
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id")Long id){
        return userRepository.findById(id).get();
    }


    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser( @ModelAttribute("user") User user){
       return userRepository.save(user);
    }

    @PostMapping("create")
    public User createUser( @RequestBody User user){
        String encodedPassword=passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
       return userRepository.save(user);

    }

}
