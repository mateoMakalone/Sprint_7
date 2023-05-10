package ru.scooter.order;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
public class OrderGenerator {
    public static Order getOrderWithBlackNGreyColor(){
        final String firstName = RandomStringUtils.randomAlphabetic(3,10);
        final String lastName = RandomStringUtils.randomAlphabetic(3,10);
        final String address = RandomStringUtils.randomAlphabetic(3,10);
        final String metroStation = RandomStringUtils.randomAlphabetic(3,10);
        final String phone = RandomStringUtils.randomAlphabetic(10,12);
        final int rentTime = 4;
        final String deliveryDate = "123456";
        final String comment = RandomStringUtils.randomAlphabetic(10,20);
        final String[] color = {"BLACK", "GREY"};
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
    public static Order getOrderWithBlackColor(){
        final String firstName = RandomStringUtils.randomAlphabetic(3,10);
        final String lastName = RandomStringUtils.randomAlphabetic(3,10);
        final String address = RandomStringUtils.randomAlphabetic(3,10);
        final String metroStation = RandomStringUtils.randomAlphabetic(3,10);
        final String phone = RandomStringUtils.randomAlphabetic(10,12);
        final int rentTime = 4;
        final String deliveryDate = "123456";
        final String comment = RandomStringUtils.randomAlphabetic(10,20);
        final String[] color = {"BLACK"};
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
    public static Order getOrderWithGreyColor(){
        final String firstName = RandomStringUtils.randomAlphabetic(3,10);
        final String lastName = RandomStringUtils.randomAlphabetic(3,10);
        final String address = RandomStringUtils.randomAlphabetic(3,10);
        final String metroStation = RandomStringUtils.randomAlphabetic(3,10);
        final String phone = RandomStringUtils.randomAlphabetic(10,12);
        final int rentTime = 4;
        final String deliveryDate = "123456";;
        final String comment = RandomStringUtils.randomAlphabetic(10,20);
        final String[] color = {"GREY"};
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
    public static Order getOrderWithoutColor(){
        final String firstName = RandomStringUtils.randomAlphabetic(3,10);
        final String lastName = RandomStringUtils.randomAlphabetic(3,10);
        final String address = RandomStringUtils.randomAlphabetic(3,10);
        final String metroStation = RandomStringUtils.randomAlphabetic(3,10);
        final String phone = RandomStringUtils.randomAlphabetic(10,12);
        final int rentTime = 4;
        final String deliveryDate = "123456";
        final String comment = RandomStringUtils.randomAlphabetic(10,20);
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, null);
    }
}
