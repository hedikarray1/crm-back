package com.societe2icom.crmbackend.Repository;

import com.societe2icom.crmbackend.Entities.Employee;
import com.societe2icom.crmbackend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {


    @Query("SELECT e FROM Employee e where e.User= :user")
   public Employee findByUser(@Param("user") User userId);

}
