package com.mercan.anil.container;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/get-container")
public class ContainerResource {

    @Inject
    ContainerService containerService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getContainer(){
        return "you are running on" + containerService.getContainerId();
    }
}
