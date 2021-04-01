package com.societe2icom.crmbackend.Repository;

import com.societe2icom.crmbackend.Entities.Company;
import com.societe2icom.crmbackend.Entities.CompanyConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyConfigRepository extends JpaRepository<CompanyConfig,Long> {


    @Query("select Cc from  CompanyConfig  Cc where Company = :company")
    public CompanyConfig getCompanyConfigByCompany(@Param("company") Company company);
}