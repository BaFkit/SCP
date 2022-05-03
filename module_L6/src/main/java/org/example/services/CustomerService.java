package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements EntityService<Customer> {

    private CustomerRepository customerRepository;

    @Override
    public Customer getById(Long id) {
        List<Customer> list = (List<Customer>) customerRepository.findAll();
        return list.stream().filter(e -> e.getId().equals(id)).findFirst().get();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getEntityAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer getByName(String name) {
        return customerRepository.findByName(name);
    }

}
