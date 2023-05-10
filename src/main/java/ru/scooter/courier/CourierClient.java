package ru.scooter.courier;

import io.restassured.response.ValidatableResponse;
import ru.scooter.RestClient;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestClient {
    private static final String COURIER_PATH = "api/v1/courier/";
    private static final String COURIER_LOGIN = "api/v1/courier/login";
    private static final String COURIER_DELETE = "api/v1/courier/";

    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }
    public ValidatableResponse login(CourierCredentials credentials){
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_LOGIN)
                .then();
    }
    public ValidatableResponse delete(int id){
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_DELETE + id)
                .then();
    }
}
