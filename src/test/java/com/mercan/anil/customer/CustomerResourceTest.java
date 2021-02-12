package com.mercan.anil.customer;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static io.restassured.RestAssured.given;


@QuarkusTest
class CustomerResourceTest {

    private static final String CUSTOMER_ENDPOINT = "/customers";

    static CustomerRepository mock;

    @BeforeAll
    public static void setup() throws CustomerException {
        mock = Mockito.mock(CustomerRepository.class);
        Mockito.when(mock.deleteCustomer(123)).thenReturn(true);
        QuarkusMock.installMockForType(mock, CustomerRepository.class);
    }

    @Test
    public void delete_endpoint() throws CustomerException {
        Mockito.when(mock.deleteCustomer(123)).thenReturn(true);

        given()
                .pathParam("id" , 123)
                .when()
                .delete(CUSTOMER_ENDPOINT + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void delete_endpoint_when_no_content() throws CustomerException {
        Mockito.when(mock.deleteCustomer(123)).thenReturn(false);

        given()
                .pathParam("id" , 123)
                .when()
                .delete(CUSTOMER_ENDPOINT + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}