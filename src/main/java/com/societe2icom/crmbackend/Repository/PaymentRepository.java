package com.societe2icom.crmbackend.Repository;

import com.societe2icom.crmbackend.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
