package com.mercan.anil.fruit;


import com.mercan.anil.fruit.Fruit;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
class FruitResourceTest {
    final String FRUIT_ENDPOINT = "/fruit";

    private static Fruit sampleFruit;

    @BeforeAll
    public static void setup(){
        sampleFruit = new Fruit();
        sampleFruit.season = "summer";
        sampleFruit.name = "deneme";
    }

    @Test
    public void testHelloEndpoint() {
        given()
            .when()
                .get(FRUIT_ENDPOINT)
            .then()
                .statusCode(200)
                .body("",equalTo(Collections.emptyList()))
                .and()
                .header("Content-Length" , "2");
    }

    @Test
    public void test_saveFruit() {
        PanacheMock.mock(Fruit.class);
        Mockito.mock(Fruit.class);
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .when()
                .body(sampleFruit)
                .post(FRUIT_ENDPOINT)
            .then()
                .statusCode(HttpStatus.SC_OK);
    }


    @Test
    public void test_deleteFruit() {
        PanacheMock.mock(Fruit.class);
        Mockito.when(Fruit.deleteById(123L)).thenReturn(true);
            given()
                .when()
                    .delete("/fruit/123")
                .then()
                    .statusCode(200);
    }


}