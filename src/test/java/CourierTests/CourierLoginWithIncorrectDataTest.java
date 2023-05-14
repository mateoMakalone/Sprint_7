package CourierTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.scooter.courier.Courier;
import ru.scooter.courier.CourierClient;
import ru.scooter.courier.CourierCredentials;

@RunWith(Parameterized.class)
public class CourierLoginWithIncorrectDataTest {
    private static Courier courier;
    private CourierClient courierClient;
    private Courier correctDataCourier;
    private int courierId;
    public CourierLoginWithIncorrectDataTest(Courier courier){
        this.courier = courier;
    }
    @Before
    public void setUp(){
        courierClient = new CourierClient();
        correctDataCourier = new Courier("Saske", "Uchiha", "Naruto");
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {courier = new Courier("Sasky", "Uchiha", "Naruto")},
                {courier = new Courier("Saske", "Uchika", "Naruto")},
        };
    }
    @Test
    @DisplayName("Логин с некорректными данными в полях логин, пароль")
    @Description("Ошибка в теле ответа Учетная запись не найдена, код ответа 404")
    public void courierLoginWithIncorrectData(){
        ValidatableResponse createResponse = courierClient.create(correctDataCourier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(correctDataCourier));
        courierId = loginResponse.extract().path("id");
        ValidatableResponse incorrectLoginResponse = courierClient.login(CourierCredentials.from(courier));
        incorrectLoginResponse.log().all().body("message", Matchers.equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }
    @After
    public void cleanUp(){
        courierClient.delete(courierId);
    }
}
