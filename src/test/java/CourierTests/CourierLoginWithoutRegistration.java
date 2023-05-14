package CourierTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.scooter.courier.Courier;
import ru.scooter.courier.CourierClient;
import ru.scooter.courier.CourierCredentials;
import ru.scooter.courier.CourierGenerator;

public class CourierLoginWithoutRegistration {
    private CourierClient courierClient;
    private Courier courier;
    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandom();
    }
    @Test
    @DisplayName("Невозможно авторизоваться под несуществующей учетной записью")
    @Description("Невозможно авторизоваться, код ответа 404, Учетная запись не найдена")
    public void courierCantLoginWithoutRequierField(){
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        loginResponse.log().all().body("message", Matchers.equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

}
