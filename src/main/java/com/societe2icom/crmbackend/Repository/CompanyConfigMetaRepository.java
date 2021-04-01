package com.societe2icom.crmbackend.Repository;

import com.societe2icom.crmbackend.Entities.Company;
import com.societe2icom.crmbackend.Entities.CompanyConfigMeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyConfigMetaRepository extends JpaRepository<CompanyConfigMeta,Long> {

}