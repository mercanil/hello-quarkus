package com.mercan.anil.customer;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomerExceptionHandler implements ExceptionMapper<CustomerException> {

    @Override
    public Response toResponse(CustomerException customerException) {
        return Response.status(Response.Status.BAD_REQUEST).entity(customerException.getMessage()).build();
    }
}
