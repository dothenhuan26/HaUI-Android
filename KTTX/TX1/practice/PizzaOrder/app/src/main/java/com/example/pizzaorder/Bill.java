package com.example.pizzaorder;

import android.icu.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

public class Bill {
    private String name;
    private String phone;
    private Date date;
    private String type;
    private String shape;
    private String[] topping;

    @Override
    public String toString() {
        return "Name: "+getName()+", Phone: "+getPhone()+", Date: "+getDateFormat()+", Type: "+getType()+", Shape: "+getShape()+", Topping: "+getToppingText();
    }

    public Bill(String name, String phone, Date date, String type, String shape, String[] topping) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.type = type;
        this.shape = shape;
        this.topping = topping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public String getDateFormat() {
        SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
        return dft.format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String[] getTopping() {
        return topping;
    }

    public String getToppingText() {
        String s="";
        for (int i=0; i<getTopping().length; i++) {
            s+=getTopping()[i]+", ";
        }
        return s;
    }

    public void setTopping(String[] topping) {
        this.topping = topping;
    }
}
