package com.societe2icom.crmbackend.Repository;


import com.societe2icom.crmbackend.Entities.Feature;
import com.societe2icom.crmbackend.Entities.User;
import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature,Long> {

    @Query(value = "SELECT   JSON_OBJECT('features', CONCAT('[',GROUP_CONCAT(JSON_OBJECT('id',f.id,'name',f.name,'group_name',f.group_name)),']') ,'group_name', f.group_name) FROM `feature` f GROUP BY f.group_name",nativeQuery = true)
    List<Object> getAllGroupeName();

    @Query("SELECT DISTINCT f.GroupName FROM Feature f GROUP BY f.GroupName")
    List<JSONObject> getAllGroupeName2();
}
