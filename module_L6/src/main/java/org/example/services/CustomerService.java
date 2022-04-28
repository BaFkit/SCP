package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.repository.EntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements EntityService<Customer>{

    private EntityRepository<Customer, Product> customerEntityRepository;

    @Override
    public Customer getById(Long id) {
        return customerEntityRepository.getById(id);
    }

    @Override
    public void add(Customer customer) {
        customerEntityRepository.add(customer);
    }

    @Override
    public List<Customer> getEntityAll() {
        return customerEntityRepository.getAll();
    }

    @Override
    public void update(Customer customer) {
        customerEntityRepository.update(customer);
    }

    @Override
    public void remove(Long id) {
        customerEntityRepository.remove(id);
    }

    @Override
    public Customer getByName(String name) {
        return customerEntityRepository.getByName(name);
    }

}
