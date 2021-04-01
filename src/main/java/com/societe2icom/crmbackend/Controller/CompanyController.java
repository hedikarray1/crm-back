package com.societe2icom.crmbackend.Controller;


import com.societe2icom.crmbackend.Entities.Company;
import com.societe2icom.crmbackend.Entities.Employee;
import com.societe2icom.crmbackend.Entities.User;
import com.societe2icom.crmbackend.Repository.CompanyRepository;
import com.societe2icom.crmbackend.Repository.EmployeeRepository;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/companies/")
public class CompanyController {

    @Autowired
    public CompanyRepository companyRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
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
/*
    @GetMapping("{id}")
    public ResponseEntity getCompanyById(@PathVariable(name = "id") int id){

            return  ResponseEntity.ok().body(companyRepository.findById((long)id));


    }
*/
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

    @GetMapping("my_company")
public ResponseEntity getMyCompany(){
    User currentUser= userController.getUserFromRequest();
    if (currentUser!=null){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("company",employeeRepository.findByUser(currentUser).getCompany());
        jsonObject.put("role",employeeRepository.findByUser(currentUser).getRole());
return ResponseEntity.ok().body(jsonObject);
    }else{
        return  ResponseEntity.notFound().build();
    }
}
@PostMapping(name="test_config",consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity testConfig( @RequestBody JSONObject oldObject){

        oldObject.keySet().forEach(key->{
           Boolean jedi= oldObject.get(key) instanceof Integer;
        });


return ResponseEntity.ok().body(null);
}

public Map<String,Object> extractClass(JSONObject json,String name){
    Map<String,Object> myMap=new  HashMap<String,Object>();
    json.keySet().forEach(key->{
        if(json.get(key) instanceof Integer || json.get(key) instanceof Long ){
            String objectLine="private Long "+key+" ;\n";
            if(myMap.containsKey(name)){

            }else{

                myMap.put(name,objectLine);
            }
        }
    });
return myMap;
}
@GetMapping("test_first_tester_db")
public ResponseEntity exaaatractClass(){
  /*  Map<String,Object> myMap=new  HashMap<String,Object>();
    json.keySet().forEach(key->{
        if(json.get(key) instanceof Integer || json.get(key) instanceof Long ){
            String objectLine="private Long "+key+" ;\n";
            if(myMap.containsKey(name)){

            }else{

                myMap.put(name,objectLine);
            }
        }
    });
*/
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
    dataSource.setUrl("jdbc:mariadb://51.210.46.0:3306/tester");
    dataSource.setUsername("tester");
    dataSource.setPassword("Poimlknbv137852i");

    JdbcTemplate template = new JdbcTemplate(dataSource);

    String myRequest="SELECT pp.ID AS id,pp.post_parent AS parent_id, pp.post_title AS name, pp.post_content AS description, pp.post_excerpt AS short_description, MAX( CASE WHEN wpm.meta_key = '_stock_status' THEN wpm.meta_value END ) 'stock_status', MAX( CASE WHEN wpm.meta_key = '_regular_price' THEN wpm.meta_value END ) 'regular_price', MAX( CASE WHEN wpm.meta_key = '_price' THEN wpm.meta_value END ) 'price', MAX( CASE WHEN wpm.meta_key = '_product_attributes' THEN wpm.meta_value END ) 'attributes', ( SELECT CONCAT( '[', GROUP_CONCAT( JSON_OBJECT( 'id', wpim.ID, 'name', wpim.post_title, 'src', wpim.guid ) ), ']' ) FROM wp_posts wpim WHERE wpim.ID = MAX( CASE WHEN wpm.meta_key = '_thumbnail_id' THEN wpm.meta_value END ) ) AS images, ( SELECT CONCAT( '[', GROUP_CONCAT( JSON_OBJECT( 'id', wt.term_id, 'name', wt.name, 'slug', wt.slug ) ), ']' ) FROM wp_posts wp JOIN wp_term_relationships wtr ON wtr.object_id = wp.ID JOIN wp_term_taxonomy wtt ON wtt.term_taxonomy_id = wtr.term_taxonomy_id JOIN wp_terms wt ON wt.term_id = wtt.term_id WHERE wp.ID = pp.ID AND wtt.taxonomy = 'product_cat' ) AS categories, ( SELECT wt.name FROM wp_posts wp JOIN wp_term_relationships wtr ON wtr.object_id = wp.ID JOIN wp_term_taxonomy wtt ON wtt.term_taxonomy_id = wtr.term_taxonomy_id JOIN wp_terms wt ON wt.term_id = wtt.term_id WHERE wp.ID = pp.ID AND wtt.taxonomy = 'product_type' ) AS type, ( SELECT COALESCE(  CONCAT(  '[', GROUP_CONCAT(wp1.id SEPARATOR ','), ']' ), '[]' ) FROM wp_posts wp1 WHERE  wp1.post_parent = pp.ID AND wp1.post_type = 'product_variation' AND TYPE = 'variable' ) AS variations, ( SELECT IF(  wwpml.min_price < wwpml.max_price, CONCAT(  '<span>',  CAST(  wwpml.min_price AS DECIMAL(10, 2) ), wo.option_value,  ' - ',  CAST(  wwpml.max_price AS DECIMAL(10, 2) ), wo.option_value,  '</span>' ),  CONCAT(  '<span>', CAST( wwpml.min_price AS DECIMAL(10, 2) ),   wo.option_value,                     '</span>' ) )     FROM     wp_wc_product_meta_lookup wwpml,     wp_options wo WHERE     wwpml.product_id = pp.ID AND wo.option_name = 'woocommerce_currency' ) AS price_html ,     (  SELECT     wo.option_value    FROM wp_options wo WHERE wo.option_name = 'woocommerce_currency' ) AS currency , ( SELECT     CAST(    wwpml.min_price AS DECIMAL(10, 2))     FROM     wp_wc_product_meta_lookup wwpml, wp_options wo     WHERE     wwpml.product_id = pp.ID AND wo.option_name = 'woocommerce_currency' ) AS min_price, (SELECT    CAST(     wwpml.max_price AS DECIMAL(10, 2))    FROM    wp_wc_product_meta_lookup wwpml,    wp_options wo WHERE wwpml.product_id = pp.ID AND wo.option_name = 'woocommerce_currency' ) AS max_price    FROM     wp_posts pp  JOIN wp_postmeta wpm ON pp.ID = wpm.post_id WHERE post_type = 'product' AND post_status = 'publish' AND post_parent = 0 GROUP BY pp.ID";
   //String myRequest="select * from wp_users limit 10 ";
    List<Map<String,Object>> sqlRowSet = template.queryForList(myRequest);
   // sqlRowSet.next();
    JSONArray jsonArray=new JSONArray();
for (int i=0;i<sqlRowSet.size();i++){
   Map<String,Object>myObject=sqlRowSet.get(i);
if(myObject!=null) {

    myObject.values().forEach(val->{

if(val!=null){
    if(val.toString().indexOf("[")==0){
        JSONArray jA=new JSONArray();
        try {
         jA= (JSONArray) new JSONParser().parse(val.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONParser jParser=new JSONParser();

        jA.forEach(el->{
            try {
                el=jParser.parse(el.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        val=jA;
    }
}
       });
}



    jsonArray.add(myObject);




}
    return ResponseEntity.ok().body(jsonArray);


}


    public static JSONObject objectToJSONObject(Object object){
        Object json = null;
        try {
            json = new org.json.JSONTokener(object.toString()).nextValue();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) json;
        return jsonObject;
    }


    public static JSONArray objectToJSONArray(Object object){
        Object json = null;
        try {
            json = new JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonObject = (JSONArray)  json;
        return jsonObject;
    }

    public Employee getCompanyRoleByUser(){

        return employeeRepository.findByUser(userController.getUserFromRequest());
    }


}
