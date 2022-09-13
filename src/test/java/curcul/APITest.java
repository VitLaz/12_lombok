package curcul;


import curcul.model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class APITest {
    @Test
    @DisplayName("Проверка email с помощью моделей Lombok")
    void checkSingleUserWithLombokTest() {
        UserData data = given()
                .spec(Specs.request)
                .when()
                .get("/users/10")
                .then()
                .spec(Specs.response)
                .log().body()
                .extract().as(UserData.class);
        assertEquals(10, data.getUser().getId());
    }

    @Test
    @DisplayName("Проверка email с groovy")
    void checkEmailUsingGroovy() {
        given()
                .spec(Specs.request)
                .when()
                .get("/users")
                .then()
                .spec(Specs.response)
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("charles.morris@reqres.in"));
    }


    @Test
    @DisplayName("Проверка id с groovy")
    void checkUserIdUsingGroovy() {
        given()
                .spec(Specs.request)
                .when()
                .get("/users")
                .then()
                .spec(Specs.response)
                .log().body()
                .body("data.findAll{it.id == 6}.id.flatten()",
                        hasItem(6));
    }
}
