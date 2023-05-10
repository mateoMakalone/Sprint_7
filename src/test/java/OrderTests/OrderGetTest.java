package OrderTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.scooter.order.OrderClient;

import static org.hamcrest.Matchers.notNullValue;

public class OrderGetTest {
    private OrderClient orderClient;
    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Энд поинт GET /api/v1/orders возвращает список заказов")
    public void getOrderReturnOrderList(){
        ValidatableResponse createResponse = orderClient.getOrderList();
        createResponse.assertThat().body("orders", notNullValue())
                .and()
                .statusCode(200);
    }
}
