package com.mercan.anil.customer;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RegisterForReflection
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer id;
    private String name ;
    private String surname;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
