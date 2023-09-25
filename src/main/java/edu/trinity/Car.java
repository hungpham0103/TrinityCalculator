package edu.trinity;

import java.time.Year;

public class Car {
    private final String make;
    private final String model;
    private final Year year;
    private boolean isRunning = false;
    private int miles = 0;
    public Car(String make, String model, Year year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Year getYear() {
        return year;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void start() {
        if (!isRunning()) {
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
        }
    }

    public void drive(int distance) {
        if (isRunning()) {
            miles += distance;
        }
    }

    public int getMiles() {
        return miles;
    }
}
