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

import static org.hamcrest.Matchers.notNullValue;

public class CreateAndLoginCourierTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierId;
    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandom();
    }
    @Test
    @DisplayName("Курьера можно создать")
    @Description("Код ответа: 201, в теле ответа ok: true")
    public void courierCanBeCreated(){
        ValidatableResponse createResponse = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        courierId = loginResponse.extract().path("id");
        createResponse.log().all().body("ok", Matchers.equalTo(true))
                        .and()
                        .statusCode(201);
    }
    @Test
    @DisplayName("Курьер может авторизоваться")
    @Description("Успешный вход возвращает id, код ответа 200")
    public void courierCanLogin(){
        ValidatableResponse createResponse = courierClient.create(courier);
        createResponse.statusCode(201);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        courierId = loginResponse.extract().path("id");
        loginResponse.assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }
    @After
    public void cleanUp(){
    courierClient.delete(courierId);
    }
}
