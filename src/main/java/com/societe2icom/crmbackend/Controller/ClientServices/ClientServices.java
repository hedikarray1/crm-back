package com.societe2icom.crmbackend.Controller.ClientServices;


import com.societe2icom.crmbackend.Controller.CompanyController;
import com.societe2icom.crmbackend.Controller.UserController;
import com.societe2icom.crmbackend.Entities.CompanyConfig;
import com.societe2icom.crmbackend.Repository.CompanyConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/crm_client_services/")
public class ClientServices {

    @Autowired
    UserController userController=new UserController();
    @Autowired
    CompanyController companyController=new CompanyController();
@Autowired
CompanyConfigRepository companyConfigRepository;

 @GetMapping(value = "all_clients")
public ResponseEntity getClients(){
    return null;
}


    @GetMapping(value = "all_products")
    public ResponseEntity getProducts(){
       if(getConfigByCompany().isWooCommerce()){
          return ResponseEntity.ok().body( WooCommerceController.getInstance(getConfigByCompany()).getAllProducts());
       }
       return null;
    }
   @PostMapping(value = "add_category")
    public ResponseEntity getCategories(){
      return ResponseEntity.ok().body(WooCommerceController.getInstance(getConfigByCompany()).getAllProducts()) ;

    }



public  CompanyConfig getConfigByCompany(){


     return  companyConfigRepository.getCompanyConfigByCompany(companyController.getCompanyRoleByUser().getCompany());
}

}
