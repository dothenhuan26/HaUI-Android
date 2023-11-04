package com.example.vehiclemanagement;

public class Car {
    private String carname;
    private String manufacture;

    static String id;

    public Car(String carname, String manufacture) {
        id += 1;
        this.carname = carname;
        this.manufacture = manufacture;
    }

    public Car() {
        id += 1;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    @Override
    public String toString() {
        return this.manufacture + " " + this.carname;
    }
}
