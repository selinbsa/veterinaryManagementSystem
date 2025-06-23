package dev.patika.veterinaryManagementSystem.dao.repositories;

import dev.patika.veterinaryManagementSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByEmail(String email);

    List<Customer> findByNameContainingIgnoreCase(String name);
}
