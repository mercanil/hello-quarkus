package com.mercan.anil.customer;


import com.mercan.anil.order.Order;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Customer.findAll" ,
            query = "SELECT C FROM Customer C ORDER BY C.id",
            hints = @QueryHint(name = "org.hibernate.cachable" , value = "true")
)
public class Customer {
    @Id
    @SequenceGenerator(name = "customerSequence" , sequenceName = "customerId_seq", allocationSize = 1 , initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "customerSequence")
    private Long id;

    @Column(length = 40)
    private String name ;

    @Column(length = 40)
    private String surname;

    @OneToMany(mappedBy = "customer")
    @JsonbTransient
    public List<Order> orders;

    public Customer() {
    }

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Customer(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
