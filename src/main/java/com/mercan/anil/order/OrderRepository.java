package com.mercan.anil.order;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class OrderRepository {

    @Inject
    EntityManager entityManager;

    public List<Order> findAll(Long customerId){
        return entityManager.createNamedQuery("Order.findAll" , Order.class).setParameter("customerId",customerId).getResultList();
    }

    public Order find(Long orderId){
        return entityManager.find(Order.class , orderId);
    }

    @Transactional
    public void store(Order order) {
        entityManager.persist(order);
    }

    @Transactional
    public void delete(Long id) {
        Order order = find(id);
        entityManager.remove(order);
    }

    @Transactional
    public void update(Order order) {
        entityManager.persist(order);
    }
}
