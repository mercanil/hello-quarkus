package com.mercan.anil.fruit;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Fruit extends PanacheEntity {
    public String name;
    public String season;

    public static List<Fruit> getBySeason(String season){
        return find("season" , season).list();
    }
}
