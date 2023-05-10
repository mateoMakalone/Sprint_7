package ru.scooter.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
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
        final String login = "Uchihaa";
        final String password = "Kanoha";
        final String firstName = "Saske";
        return new Courier(login, password, firstName);
    }
}

