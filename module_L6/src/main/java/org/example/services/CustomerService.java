package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements EntityService<Customer> {

    private CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public Customer getById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getEntityAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public List<Customer> getByThroughFilter(Optional<BigDecimal> param1, Optional<BigDecimal> param2) {
        return null;
    }
}
