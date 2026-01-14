package com.fooddelivery.model;
public class User {
    private int userId;
    private String username;
    private String contactNo;

    public User(int userId, String username, String contactNo) {
        this.userId = userId;
        this.username = username;
        this.contactNo = contactNo;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getContactNo() { return contactNo; }

    @Override
    public String toString() {
        return "UserID: " + userId + ", Username: " + username + ", Contact: " + contactNo;
    }
}