package com.mercan.anil.customer;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {

    @Inject
    EntityManager entityManager;

    public List<Customer> findAll(){
        return entityManager.createNamedQuery("Customer.findAll" , Customer.class).getResultList();
    }

    public Customer findByCustomerId(Long id ) throws CustomerException {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer == null){
            throw new CustomerException("Customer not found with id" + id);
        }
        return customer;
    }

    @Transactional
    public void updateCustomer(Customer customer) throws CustomerException {
        Customer foundCustomer = findByCustomerId(customer.getId());
        foundCustomer.setSurname(customer.getSurname());
        foundCustomer.setName(customer.getName());
    }

    @Transactional
    public void createCustomer(Customer customer){
        entityManager.persist(customer);
    }

    @Transactional
    public void deleteCustomer(Long id) throws CustomerException {
        Customer byCustomerId = findByCustomerId(id);
        entityManager.remove(byCustomerId);
    }

}
