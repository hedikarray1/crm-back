package com.societe2icom.crmbackend.Repository;

import com.societe2icom.crmbackend.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {

}
