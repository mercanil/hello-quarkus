package com.mercan.anil.customer;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import javax.ws.rs.core.MediaType;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;


@QuarkusTest
class CustomerResourceTest {

    private static final String CUSTOMER_ENDPOINT = "/customers";

    static CustomerRepository mock;
    static Customer customer;

    @BeforeAll
    public static void setup() {
        mock = Mockito.mock(CustomerRepository.class);
        customer = new Customer("Anil" , "Mercan");
        QuarkusMock.installMockForType(mock, CustomerRepository.class);
    }

    @Test
    public void get_endpoint(){
        Mockito.when(mock.findAll())
                .thenReturn(Collections.singletonList(new Customer("Anil" , "Mercan")));
        given()
            .contentType(MediaType.APPLICATION_JSON)
        .when()
            .get(CUSTOMER_ENDPOINT)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(containsString("Anil") , containsString("Mercan"));
    }

    @Test
    public void post_endpoint() throws CustomerException {
        doNothing().when(mock).updateCustomer(ArgumentMatchers.any());
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(customer)
        .when()
            .post(CUSTOMER_ENDPOINT)
        .then()
            .statusCode(HttpStatus.SC_CREATED);
    }


    @Test
    public void put_endpoint(){
        doNothing().when(mock).createCustomer(ArgumentMatchers.any());
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(customer)
        .when()
            .put(CUSTOMER_ENDPOINT)
        .then()
            .statusCode(HttpStatus.SC_OK);
    }


    @Test
    public void delete_endpoint() throws CustomerException {
        doNothing().when(mock).deleteCustomer(123L);

        given()
            .pathParam("id" , 123)
        .when()
            .delete(CUSTOMER_ENDPOINT + "/{id}")
        .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}