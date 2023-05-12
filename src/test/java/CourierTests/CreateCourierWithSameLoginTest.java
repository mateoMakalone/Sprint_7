package CourierTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.scooter.courier.Courier;
import ru.scooter.courier.CourierClient;
import ru.scooter.courier.CourierCredentials;
import ru.scooter.courier.CourierGenerator;

public class CreateCourierWithSameLoginTest {
    private CourierClient courierClient;
    private Courier courier;
    private int courierId;
    @Before
    public void setUp(){
        courier = CourierGenerator.getCourierClone();
        courierClient = new CourierClient();
    }
    @Test
    @DisplayName("Невозможно создать курьера с уже использующимся логином")
    @Description("Сообщение в теле ответа, код ответа 409")
    public void courierWithSameLoginCantBeCreated(){
        ValidatableResponse createResponse = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        courierId = loginResponse.extract().path("id");
        createResponse.log().all().body("ok", Matchers.equalTo(true))
                .and()
                .statusCode(201);
        ValidatableResponse createSecondResponse = courierClient.create(courier);
        createSecondResponse.log().all().body("message", Matchers.equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }
    @After
    public void cleanUp(){
        courierClient.delete(courierId);
    }
}
