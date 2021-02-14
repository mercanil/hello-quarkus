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
        Customer customer = customerRepository.createCustomer(c);
        return Response.status(Response.Status.CREATED)
                .entity(customer)
                .build();
    }

    @PUT
    public Customer update(Customer c) throws CustomerException {
        return customerRepository.updateCustomer(c);
    }

    @DELETE
    @Path("/{id}")
    public void  delete(@PathParam("id") Integer id ) throws CustomerException {
        boolean contentFound= customerRepository.deleteCustomer(id);
        if (!contentFound){
            throw new CustomerException("Not Found");
        }
    }
}
