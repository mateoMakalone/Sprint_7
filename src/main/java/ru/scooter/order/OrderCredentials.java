package ru.scooter.order;

import io.restassured.response.ValidatableResponse;

public class OrderCredentials {
    private int track;

    public OrderCredentials() {
    }

    public OrderCredentials(int track) {
        this.track = track;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }
}
