package com.mercan.anil.container;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContainerService {

    public String getContainerId() {
        return System.getenv().getOrDefault("HOSTNAME" , "unknown");
    }
}
