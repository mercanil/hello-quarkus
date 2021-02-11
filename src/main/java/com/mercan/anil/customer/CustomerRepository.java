package com.mercan.anil.customer;


import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {
    List<Customer> customerList = new ArrayList<>();
    int counter;

    public int getNextCustomerId(){
        return counter++;
    }

    public List<Customer> findAll(){
        return customerList;
    }

    public Customer findByCustomerId(Integer id ) throws CustomerException {
        return customerList.stream()
                        .filter( c -> c.getId().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new CustomerException("Customer not found"));
    }

    public Customer updateCustomer(Customer customer) throws CustomerException {
        Customer byCustomerId = findByCustomerId(customer.getId());
        byCustomerId.setName(customer.getName());
        byCustomerId.setSurname(customer.getSurname());
        return byCustomerId;
    }

    public Customer createCustomer(Customer customer){
        customer.setId(getNextCustomerId());
        customerList.add(customer);
        return customer;
    }

    public void deleteCustomer(Integer id) throws CustomerException {
        Customer byCustomerId = findByCustomerId(id);
        boolean remove = customerList.remove(byCustomerId);
        if (!remove){
            throw new CustomerException("Unable to find Customer");
        }
    }

}
