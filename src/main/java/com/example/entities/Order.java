package com.example.entities;

import java.sql.Timestamp;

public class Order {

    private int id;
    private String surname;
    private String name;
    private String phone;
    private String note;
    private Timestamp createDate;
    private boolean isComplete;

    public Order(int id, String surname, String name, String phone, String note, Timestamp createDate, boolean isComplete) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.note = note;
        this.createDate = createDate;
        this.isComplete = isComplete;
    }

    public Order() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {

        return note;
    }
}
