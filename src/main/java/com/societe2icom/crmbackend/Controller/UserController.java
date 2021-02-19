package com.societe2icom.crmbackend.Controller;


import com.societe2icom.crmbackend.Configuration.UserDetailsServiceImpl;
import com.societe2icom.crmbackend.Entities.User;
import com.societe2icom.crmbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("get_by_role/{role}")
    public List<User> getClients(@PathVariable("role") String role) {
        return userRepository.findByRole(role);
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id).get();
    }


    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@ModelAttribute("user") User user) {
        return userRepository.save(user);
    }

    @PostMapping("create")
    public ResponseEntity createUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
            User usercreated = userRepository.save(user);
            if (usercreated != null) {
                return ResponseEntity.ok().body(user);
            } else {
                return ResponseEntity.badRequest().body("veillez verifier les champs de l' utilisateur");
            }
        } catch (PersistenceException e) {
            Logger.getAnonymousLogger().log(Level.ALL, "error:" + e.getMessage());
            return ResponseEntity.status(500).body("erreur au niveau du serveur: " + e.getMessage());
        }
    }

@GetMapping("profile")
    public ResponseEntity getMyProfile(){
        User user=getUserFromRequest();
   if(user!=null){
       return ResponseEntity.ok().body(user);
   }else{
       return ResponseEntity.badRequest().body("veillez vous reconnecter");
   }
}


@DeleteMapping("{id}")
public ResponseEntity removeUserAdmin( @PathVariable(name = "id") Long id){
        User currentUser=getUserFromRequest();
      if(currentUser!=null && currentUser.getRole().equalsIgnoreCase("admin")){
         User usertodelete= new User();
        //  usertodelete.setId(id);
          User user=  userRepository.getOne(id);
          userRepository.deleteById(id);

          if(user!=null){
              return ResponseEntity.ok().body("Utilisateur supprimé avec succès");
          }else{
              return  ResponseEntity.notFound().build();
          }
      }else{
          return ResponseEntity.badRequest().body("Vous devez disposer des priorités admin pour pouvoir supprimer un utilisateur");
      }

}




public  User getUserFromRequest(){
    Object userDetails = SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
    User requestUser=userRepository.findByEmail(userDetails.toString());
  return requestUser;
}


}
