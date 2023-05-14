package ru.scooter.order;

import io.restassured.response.ValidatableResponse;
import ru.scooter.RestClient;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {
    private static final String ORDER_PATH = "api/v1/orders";
    private static final String ORDER_CANCEL = "api/v1/orders/cancel";
    public ValidatableResponse orderCreate(Order order){
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }
    public ValidatableResponse getOrderList(){
        return given()
                .spec(getBaseSpec())
                .get(ORDER_PATH)
                .then();
    }
    public ValidatableResponse cancelOrder(OrderCredentials orderCredentials){
        return given()
                .spec(getBaseSpec())
                .body(orderCredentials)
                .when()
                .put(ORDER_CANCEL)
                .then();
    }
}
