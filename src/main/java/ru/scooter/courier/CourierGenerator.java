package ru.scooter.courier;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    static Faker faker = new Faker();
    public static Courier getRandom(){
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }
    public static Courier getRandomWithoutLogin() {
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(null, password, firstName);
    }
    public static Courier getRandomWithoutPassword() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = "";
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login,password, firstName);
    }

    public static Courier getCourierClone(){
        final String login = faker.name().username();
        final String password = faker.number().digit();
        final String firstName = faker.name().firstName();
        return new Courier(login, password, firstName);
    }
}

