package com.societe2icom.crmbackend.Controller;


import com.societe2icom.crmbackend.Entities.Company;
import com.societe2icom.crmbackend.Entities.User;
import com.societe2icom.crmbackend.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/companies/")
public class CompanyController {

    @Autowired
    public CompanyRepository companyRepository;

    UserController userController=new UserController();


    @GetMapping("get_all")
    public ResponseEntity getAllCompanies()
    {
        User currentUser= userController.getUserFromRequest();
        if(currentUser!=null && currentUser.getRole().equalsIgnoreCase("admin")){
          return  ResponseEntity.ok().body(companyRepository.findAll());
        }else{
            return ResponseEntity.badRequest().body("vous devez disposer des priorités admin pour pouvoir visualiser les sociétés");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getCompanyById(@PathVariable(name = "id") int id){
        User currentUser= userController.getUserFromRequest();

        if(currentUser!=null
                &&
              //  ( currentUser.getCompany().getId()==id ||
                        currentUser.getRole().equalsIgnoreCase("admin")
                ){
            return  ResponseEntity.ok().body(companyRepository.findById((long)id));
        }else{
            return ResponseEntity.badRequest().body("vous devez disposer des priorités admin pour pouvoir visualiser les sociétés");
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity RemoveCompanyById(@PathVariable(name = "id") int id){
        User currentUser= userController.getUserFromRequest();

        if(currentUser!=null &&
              //  ( currentUser.getCompany().getId()==id ||
                        currentUser.getRole().equalsIgnoreCase("admin")
        ){
            companyRepository.deleteById((long)id);
            return  ResponseEntity.ok().body("société supprimée avec succès");
        }else{
            return ResponseEntity.badRequest().body("vous devez disposer des priorités admin pour pouvoir supprimer une société");
        }

    }

    @PostMapping(name = "create")
    public ResponseEntity createCompany(@ModelAttribute("company")Company company){
        User currentUser= userController.getUserFromRequest();

        if(currentUser!=null && currentUser.getRole().equalsIgnoreCase("admin")){
          Company savedCompany = companyRepository.save(company);
          if(savedCompany!=null){
              return ResponseEntity.ok().body(savedCompany);
          }else{
              return ResponseEntity.badRequest().body("erreur lors de la création de la société");

          }

        }else{
            return ResponseEntity.badRequest().body("vous devez disposer des priorités admin pour pouvoir créer une société");
        }
    }



}
