package com;

public class Account {
    private String phone;
    private String name;
    private String address;
    private String dateRegistered;

    public Account() {
    }

    public Account(String name, String address, String dateRegistered) {
        this.name = name;
        this.address = address;
        this.dateRegistered = dateRegistered;
    }

    public Account(Account account, String phone) {
        this.phone = phone;
        this.address = account.address;
        this.dateRegistered = account.dateRegistered;
        this.name = account.name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    @Override
    public String toString() {
        return "Account{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateRegistered='" + dateRegistered + '\'' +
                '}';
    }
}
