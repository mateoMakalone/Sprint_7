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
@RunWith(Parameterized.class)
public class CreateAndLoginCourierWithoutRequriedFieldTest {
    private static Courier courier;
    private CourierClient courierClient;
    public CreateAndLoginCourierWithoutRequriedFieldTest(Courier courier){
        this.courier = courier;
    }
    @Before
    public void setUp(){
        courierClient = new CourierClient();
    }
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {courier = CourierGenerator.getRandomWithoutLogin()},
                {courier = CourierGenerator.getRandomWithoutPassword()},
        };
    }
    @Test
    @DisplayName("Невозможно создать курьера без логина или пароля")
    @Description("Ошибка в теле ответа, код ответа 400")
    public void courierCantCreatedWithoutRequiredField(){
        ValidatableResponse createResponse = courierClient.create(courier);
        createResponse.log().all().body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }
    @Test
    @DisplayName("Невозможно авторизоваться без одного из полей")
    @Description("Если какого-то поля нет, запрос возвращает ошибку, код ответа 400")
    public void courierCantLoginWithoutRequiredField(){
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        loginResponse.log().all().body("message", Matchers.equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }
}
