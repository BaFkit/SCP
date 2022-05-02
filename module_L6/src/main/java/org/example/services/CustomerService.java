package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements EntityService<Customer>{

    private CustomerRepository customerRepository;

    @Override
    public Customer getById(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public void add(Customer customer) {
        customerRepository.add(customer);
    }

    @Override
    public List<Customer> getEntityAll() {
        return customerRepository.getAll();
    }

    @Override
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.remove(id);
    }

    @Override
    public Customer getByName(String name) {
        return customerRepository.getByName(name);
    }

}
