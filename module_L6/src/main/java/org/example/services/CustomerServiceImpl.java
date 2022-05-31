package org.example.services;

import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService<Customer> {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
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
    @Transactional(readOnly = true)
    public Customer getByLogin(String login) {
        return customerRepository.findByLogin(login).get();
    }
}
