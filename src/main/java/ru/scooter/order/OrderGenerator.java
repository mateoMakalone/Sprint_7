package ru.scooter.order;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class OrderGenerator {
    static Faker faker = new Faker();
    public static Order getOrderData(String [] color){
        final String firstName = RandomStringUtils.randomAlphabetic(3,10);
        final String lastName = RandomStringUtils.randomAlphabetic(3,10);
        final String address = RandomStringUtils.randomAlphabetic(3,10);
        final String metroStation = RandomStringUtils.randomAlphabetic(3,10);
        final String phone = RandomStringUtils.randomAlphabetic(10,12);
        final int rentTime = faker.number().numberBetween(1, 10);
        final String deliveryDate = String.valueOf(faker.number().randomDigitNotZero());
        final String comment = RandomStringUtils.randomAlphabetic(10,20);
        color = new String[]{};
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}
