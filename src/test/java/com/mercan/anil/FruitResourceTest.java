package com.mercan.anil;


import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
class FruitResourceTest {
    final String FRUIT_ENDPOINT = "/fruit";

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get(FRUIT_ENDPOINT)
                .then()
                .statusCode(200)
                .body("",equalTo(Collections.emptyList()))
                .and()
                .header("Content-Length" , "2");
    }
}