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
    private CourierClient courierClient = new CourierClient();
    private Courier courierFirst;
    private Courier courierSecond;
    private int courierId;
    @Before
    public void setUp(){
        courierFirst = CourierGenerator.getCourierClone();
        courierSecond = CourierGenerator.getCourierClone();
    }
    @Test
    @DisplayName("Невозможно создать курьера с уже использующимся логином")
    @Description("Сообщение в теле ответа, код ответа 409")
    public void courierWithSameLoginCantBeCreated(){
        ValidatableResponse createResponse = courierClient.create(courierFirst);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courierFirst));
        courierId = loginResponse.extract().path("id");
        createResponse.log().all().body("ok", Matchers.equalTo(true))
                .and()
                .statusCode(201);
        ValidatableResponse createSecondResponse = courierClient.create(courierSecond);
        createSecondResponse.log().all().body("message", Matchers.equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }
    @After
    public void cleanUp(){
        courierClient.delete(courierId);
    }
}
