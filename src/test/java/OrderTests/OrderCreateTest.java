package OrderTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.scooter.order.Order;
import ru.scooter.order.OrderClient;
import ru.scooter.order.OrderCredentials;
import ru.scooter.order.OrderGenerator;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private OrderClient orderClient;
    private static Order order;
    private OrderCredentials orderCredentials;
    private int trackNumber;
    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }
    public OrderCreateTest(Order order){
        this.order = order;
    }
    @Parameterized.Parameters
    public static Object[][]getData() {
        return new Object[][]{
                {order = OrderGenerator.getOrderData(new String[]{"BLACK"})},
                {order = OrderGenerator.getOrderData(new String[]{"GREY"})},
                {order = OrderGenerator.getOrderData(new String[]{"BLACK", "GREY"})},
                {order = OrderGenerator.getOrderData(null)},
        };
    }
    @Test
    @DisplayName("Возможность создать заказ с любой комбинацией цветов")
    @Description("можно указать один из цветов — BLACK или GREY;" +
                 "можно указать оба цвета;" +
                 "можно совсем не указывать цвет;" +
                 "Тело ответа содержит track")
    public void createOrderWithDifferentColors(){
        ValidatableResponse createResponse = orderClient.orderCreate(order);
        trackNumber = createResponse.extract().path("track");
        createResponse.assertThat().body("track", notNullValue())
                .and()
                .statusCode(201);
    }
    @After
    public void cleanUp(){
      orderCredentials = new OrderCredentials(trackNumber);
      orderClient.cancelOrder(orderCredentials);
    }
}
