package com.mercan.anil.customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject CustomerRepository customerRepository;

    @GET
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    @POST
    public Response store(Customer c){
        customerRepository.createCustomer(c);
        return Response.status(Response.Status.CREATED)
                .build();
    }

    @PUT
    public Response update(Customer c) throws CustomerException {
        return Response.status(Response.Status.OK)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response  delete(@PathParam("id") Long id ) throws CustomerException {
        customerRepository.deleteCustomer(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
