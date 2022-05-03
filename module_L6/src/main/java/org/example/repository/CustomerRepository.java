package org.example.repository;

import org.example.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByName(String name);

}