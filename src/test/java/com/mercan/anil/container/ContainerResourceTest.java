package com.mercan.anil.container;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ContainerResourceTest {

    @Test
    void getContainer() {

        given()
                .when().get("/get-container")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }
}