package com.example.bai46;

import androidx.annotation.NonNull;

public class Staff {
    private String ID;
    private String name;
    private String type;

    public Staff(String ID, String name, String type) {
        this.ID = ID;
        this.name = name;
        this.type = type;
    }

    public Staff() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @NonNull
    @Override
    public String toString() {
        return getType() + ": " + getID() + " - " + getName() + " - " + (getType().equals("full") ? "luong 500" : "Luong 150");
    }
}
