package com.societe2icom.crmbackend.Controller.ClientServices;

import com.societe2icom.crmbackend.Entities.CompanyConfig;
import com.societe2icom.crmbackend.Entities.WoocommerceEntities.*;
import liquibase.pro.packaged.W;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WooCommerceController {

    private static WooCommerceController Instance=null;
   private CompanyConfig companyConfig;
private  DriverManagerDataSource dataSource;
    private WooCommerceController(CompanyConfig companyConfig){
        this.companyConfig=companyConfig;




       this.dataSource = new DriverManagerDataSource();
       /*
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://51.210.46.0:3306/tester");
        dataSource.setUsername("tester");
        dataSource.setPassword("Poimlknbv137852i");
*/
        dataSource.setDriverClassName(companyConfig.getDBDriver());
        dataSource.setUrl("jdbc:mariadb://"+companyConfig.getServerUrl()+":"+companyConfig.getDBPort()+"/"+companyConfig.getDBName());
        dataSource.setUsername(companyConfig.getDBUsername());
        dataSource.setPassword(companyConfig.getDBPassword());

        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);


        jdbcTemplate.execute("create TABLE IF NOT EXISTS Embalage (ID int NOT NULL AUTO_INCREMENT, title varchar(500) , volume DOUBLE NOT NULL , height DOUBLE NOT NULL , ammount DOUBLE NOT NULL , length DOUBLE NOT NULL , price DOUBLE NOT NULL, min_ammount DOUBLE NOT NULL , width DOUBLE NOT NULL ,description varchar(500) ,type varchar(500), reference varchar(500), PRIMARY KEY (ID))  ENGINE = InnoDB; ");
        jdbcTemplate.execute("create TABLE IF NOT EXISTS EmbalageProduit (ID int NOT NULL AUTO_INCREMENT,id_produit int, id_embalage int, PRIMARY KEY (ID)) ENGINE = InnoDB; ");
        jdbcTemplate.execute("create TABLE IF NOT EXISTS Etiquette (ID int NOT NULL AUTO_INCREMENT, reference varchar(500), ammount int NOT NULL,  PRIMARY KEY (ID)) ENGINE = InnoDB; ");


    }

    public static WooCommerceController getInstance(CompanyConfig configuration){

    Instance=new WooCommerceController(configuration);

return Instance;
    }




    public JSONArray getAllProducts(){
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
        return jsonArray;
    }

    public List<Map<String,Object>> getAllUsers(){
        return null;
    }
    public List<Map<String,Object>> getAllCategories(){
        return null;
    }
    public List<Map<String,Object>> getAllPromos(){
        return null;
    }
    public List<Map<String,Object>> getUserById(Long id){
        return null;
    }
    public List<Map<String,Object>> getProductById(Long id){
        return null;
    }
    public List<Map<String,Object>> getProductsByCategory(Long catId){
        return null;
    }
    public List<Map<String,Object>> getCategoriesOfProduct(Long prodId){
        return null;
    }

    public boolean createProduct(JSONObject product) throws JSONException {
       String postname=product.getString("title").replace(' ','-');
       Date date=new Date();
        String sql_wp_post="INSERT INTO wp_posts " +
   "(post_author, post_date, post_date_gmt, post_content, post_title, post_excerpt, post_status, comment_status, ping_status, post_password, post_name, to_ping, pinged, post_modified, post_modified_gmt, post_content_filtered, post_parent, guid, menu_order, post_type, post_mime_type, comment_count) " +
   "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";



        KeyHolder keyHolder= new GeneratedKeyHolder();
JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
jdbcTemplate.update(connection -> {
    PreparedStatement ps = connection.prepareStatement(sql_wp_post, new String[] { "ID" });
    try {
    ps.setInt(1, 0);
    ps.setDate(2, java.sql.Date.valueOf(date.toString()));
    ps.setDate(3, java.sql.Date.valueOf(date.toGMTString()));
        ps.setString(4,product.getString("content"));
    ps.setString(5, product.getString("title"));
    ps.setString(6, product.getString("description"));
    ps.setString(7, "publish");
    ps.setString(8, "closed");
    ps.setString(9, "closed");
    ps.setString(10, "");
    ps.setString(11,postname);
    ps.setString(12, "");
    ps.setString(13, "");
        ps.setDate(14, java.sql.Date.valueOf(date.toString()));
        ps.setDate(15, java.sql.Date.valueOf(date.toGMTString()));
    ps.setString(16, "");
    ps.setInt(17, 0);
    ps.setString(18, "aaa");
    ps.setInt(19, 0);
    ps.setString(20,"product");
    ps.setString(21, "");
    ps.setInt(22, 0);


    } catch (JSONException e) {
        e.printStackTrace();
    }
    return ps;
}, keyHolder);

long postId=keyHolder.getKey().longValue();

        String sql_wp_post_picture="INSERT INTO wp_posts " +
                "(post_author, post_date, post_date_gmt, post_content, post_title, post_excerpt, post_status, comment_status, ping_status, post_password, post_name, to_ping, pinged, post_modified, post_modified_gmt, post_content_filtered, post_parent, guid, menu_order, post_type, post_mime_type, comment_count) " +
                "VALUES (0,"+ date+","+date+","+product.getString("picture_description")+","+product.getString("picture_name")+","+product.getString("picture_legend")+",\t\n" +
                "inherit,closed,closed,'',"+product.getString("picture_name")+"-0"+",'','',"+date+","+date+",'',0,'https://stupefied-johnson.51-210-46-0.plesk.page/wp-content/uploads/2020/09/5.png',0,'attachment','',0)";


        String wp_wc_product_meta_lookup="INSERT INTO wp_wc_product_meta_lookup" +
                "(product_id, sku, virtual, downloadable, min_price, max_price, onsale, stock_quantity, stock_status, rating_count, average_rating, total_sales, tax_status, tax_class) " +
                "VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9],[value-10],[value-11],[value-12],[value-13],[value-14])";





return true;
    }


    private wp_term insert_wp_terms(  wp_term wp_term){
wp_term.setTerm_id(0);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        NamedParameterJdbcTemplate jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate.update("Insert  into wp_terms (term_id,name, slug, term_group) VALUES (:term_id,:name, :slug, :term_group) ",new BeanPropertySqlParameterSource(wp_term),keyHolder);
wp_term.setTerm_id(keyHolder.getKey().longValue());
return wp_term;
    }


    private wp_term_taxonomy insert_wp_term_taxonomy(wp_term_taxonomy term_taxonomy){
       term_taxonomy.setTerm_taxonomy_id(0);
        KeyHolder keyHolder=new GeneratedKeyHolder();

        NamedParameterJdbcTemplate jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO wp_term_taxonomy( term_taxonomy_id,term_id, taxonomy, description, parent, count) VALUES ( :term_taxonomy_id,:term_id, :taxonomy, :description, :parent, 0) ",new BeanPropertySqlParameterSource(term_taxonomy),keyHolder);
        term_taxonomy.setTerm_taxonomy_id(keyHolder.getKey().longValue());
        return term_taxonomy;
    }

    private wp_posts insert_wp_posts(wp_posts wp_posts){
        wp_posts.setID(0);
        KeyHolder keyHolder=new GeneratedKeyHolder();

        NamedParameterJdbcTemplate jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO wp_posts(ID,post_author, post_date, post_date_gmt, post_content, post_title, post_excerpt, post_status, comment_status, ping_status, post_password, post_name, to_ping, pinged, post_modified, post_modified_gmt, post_content_filtered, post_parent, guid, menu_order, post_type, post_mime_type, comment_count) VALUES (:post_author, :post_date, :post_date_gmt, :post_content, :post_title, :post_excerpt, :post_status, :comment_status, :ping_status, :post_password, :post_name, :to_ping, :pinged, :post_modified, :post_modified_gmt, :post_content_filtered, :post_parent, :guid, :menu_order, :post_type, :post_mime_type, :comment_count) ",new BeanPropertySqlParameterSource(wp_posts),keyHolder);
        wp_posts.setID(keyHolder.getKey().longValue());
        return wp_posts;
    }

    private wp_postmeta insert_wp_postmeta(wp_postmeta wp_postmeta){
        wp_postmeta.setMeta_id(0);
        KeyHolder keyHolder=new GeneratedKeyHolder();

        NamedParameterJdbcTemplate jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate.update( "INSERT INTO wp_postmeta ( meta_id,post_id, meta_key, meta_value) VALUES ( :meta_id,:post_id, :meta_key, :meta_value)",new BeanPropertySqlParameterSource(wp_postmeta),keyHolder);
        wp_postmeta.setMeta_id(keyHolder.getKey().longValue());
        return wp_postmeta;
    }

    private wp_termmeta insert_wp_termmeta(wp_termmeta wp_termmeta){
        wp_termmeta.setMeta_id(0);
        KeyHolder keyHolder=new GeneratedKeyHolder();

        NamedParameterJdbcTemplate jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate.update( "INSERT INTO wp_termmeta(meta_id,term_id, meta_key, meta_value) VALUES (:meta_id,:term_id, :meta_key, :meta_value)",new BeanPropertySqlParameterSource(wp_termmeta),keyHolder);
        wp_termmeta.setMeta_id(keyHolder.getKey().longValue());
        return wp_termmeta;
    }

    private wp_term_relationships insert_wp_term_relationships(wp_term_relationships wp_term_relationships){

        KeyHolder keyHolder=new GeneratedKeyHolder();

        NamedParameterJdbcTemplate jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate.update( "INSERT INTO wp_term_relationships(object_id, term_taxonomy_id, term_order) VALUES wp_term_relationships(:object_id, :term_taxonomy_id, :term_order)",new BeanPropertySqlParameterSource(wp_term_relationships),keyHolder);
        wp_term_relationships.setObject_id(keyHolder.getKey().longValue());
        return wp_term_relationships;
    }



    public JSONObject insertCategory(wp_term wp_term, wp_term_taxonomy wp_term_taxonomy,wp_posts wp_posts,ArrayList<wp_postmeta> posts_meta_list,ArrayList<wp_termmeta> terms_meta_list ) throws JSONException {
        JSONObject category=new JSONObject();
        //wp_term

        wp_term inserted_term=insert_wp_terms(wp_term);
        category.put("id",inserted_term.getTerm_id());
        category.put("name",inserted_term.getName());
        category.put("slug",inserted_term.getSlug());

        //wp_term_taxonomy
        wp_term_taxonomy.setTerm_id(inserted_term.getTerm_id());
        wp_term_taxonomy.setTaxonomy("product_cat");
        wp_term_taxonomy inserted_term_taxonomy=insert_wp_term_taxonomy(wp_term_taxonomy);
        category.put("description",inserted_term_taxonomy.getDescription());
        category.put("parent",inserted_term_taxonomy.getParent());
        category.put("count",inserted_term_taxonomy.getCount());

        //wp_posts
      wp_posts inserted_post=insert_wp_posts(wp_posts);
      JSONObject image=new JSONObject();
      image.put("date_created",wp_posts.getPost_date());
      image.put("date_created_gmt",wp_posts.getPost_date_gmt());
        image.put("date_modified",wp_posts.getPost_modified());
      image.put("date_modified_gmt",wp_posts.getPost_date_gmt());
      image.put("name",wp_posts.getPost_title());
      image.put("id",inserted_post.getID());

      //wp_post_meta
      posts_meta_list.forEach(el->{
          el.setPost_id(inserted_post.getID());
          insert_wp_postmeta(el);
          if(el.getMeta_key().equalsIgnoreCase("_wp_attached_file")){
              try {
                  image.put("src",el.getMeta_value());
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
      });

     insert_wp_termmeta(new wp_termmeta(inserted_term.getTerm_id(),"thumbnail_id",String.valueOf(inserted_post.getID())));
      category.put("image",image);


        //wp term_meta
        terms_meta_list.forEach(el->{
            el.setTerm_id(inserted_term.getTerm_id());

            insert_wp_termmeta(el);
            if(el.getMeta_key().equalsIgnoreCase("display_type")){
                if(el.getMeta_value().equalsIgnoreCase("")||el.getMeta_value()==null){
                    el.setMeta_value("default");
                }
                try {
                    category.put("display",el.getMeta_value());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(el.getMeta_key().equalsIgnoreCase("order")){

                try {
                    category.put("menu_order",el.getMeta_value());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return category;

    }



    public JSONObject insertProduct( ){
        JSONObject product=new JSONObject();

        return product;
    }





}
