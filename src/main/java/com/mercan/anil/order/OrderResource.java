package com.mercan.anil.order;

import com.mercan.anil.customer.Customer;
import com.mercan.anil.customer.CustomerException;
import com.mercan.anil.customer.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("orders")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    @Inject OrderRepository orderRepository;
    @Inject CustomerRepository customerRepository;

    @GET
    public List<Order> getAll(@QueryParam("customerId") Long customerId){
        return orderRepository.findAll(customerId);
    }

    @POST
    @Path("/{customer}")
    public Response storeOrder(Order order , @PathParam("customer") Long customerId) throws CustomerException {
        Customer customer = customerRepository.findByCustomerId(customerId);
        order.setCustomer(customer);
        orderRepository.store(order);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response update(Order order){
        orderRepository.update(order);
        return  Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        orderRepository.delete(id);
        return  Response.status(Response.Status.NO_CONTENT).build();
    }
}
