package com.societe2icom.crmbackend.Controller;



import com.societe2icom.crmbackend.Entities.Feature;
import com.societe2icom.crmbackend.Entities.User;
import com.societe2icom.crmbackend.Repository.FeatureRepository;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/features/")
public class FeatureController {

    @Autowired
    public FeatureRepository featureRepository;

    @Autowired
    UserController userController=new UserController();

    @GetMapping("get_all")
    public ResponseEntity getAllFeatures()
    {
        User currentUser= userController.getUserFromRequest();
        if(currentUser!=null && currentUser.getRole().equalsIgnoreCase("super_admin")){
            return  ResponseEntity.ok().body(featureRepository.findAll());
        }else{
            return ResponseEntity.badRequest().body("vous devez disposer des priorités admin pour pouvoir visualiser les fonctionnalités");
        }

    }

    @GetMapping("get_all_groupe_name")
    public ResponseEntity getAllGroupeName()
    {
        User currentUser= userController.getUserFromRequest();
        if(currentUser!=null && currentUser.getRole().equalsIgnoreCase("super_admin")){
          Map<String, List<Feature>> myMap=new HashMap<String,List<Feature>>();
            featureRepository.findAll().forEach(le-> {
                        if (myMap.containsKey(le.getGroupName())) {
                            myMap.get(le.getGroupName()).add(le);
                        } else {
                            List<Feature> newList=new ArrayList<>();
                            newList.add(le);
myMap.put(le.getGroupName(),newList);
                        }
                    }
                );
            JSONArray jsonArray=new JSONArray();
myMap.keySet().forEach(el->{
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("group_name",el);
    jsonObject.put("features",myMap.get(el));

    jsonArray.add(jsonObject);
});


            return  ResponseEntity.ok().body(jsonArray);
        }else{
            return ResponseEntity.badRequest().body("vous devez disposer des priorités admin pour pouvoir visualiser les fonctionnalités");
        }

    }

    @PostMapping("create")
    public ResponseEntity createFeature(@RequestBody Feature feature) {
        User currentUser= userController.getUserFromRequest();
        if(currentUser!=null && currentUser.getRole().equalsIgnoreCase("super_admin")){
        try {
            Feature featurecreated = featureRepository.save(feature);
            if (featurecreated != null) {
                return ResponseEntity.ok().body(feature);
            } else {
                return ResponseEntity.badRequest().body("erreur lors de la création de la fonctionnalité");
            }
        } catch (PersistenceException e) {
            Logger.getAnonymousLogger().log(Level.ALL, "error:" + e.getMessage());
            return ResponseEntity.status(500).body("erreur au niveau du serveur: " + e.getMessage());
        }
        }else{
            return ResponseEntity.badRequest().body("vous devez disposer des priorités admin pour pouvoir visualiser les fonctionnalités");
        }
    }
}
