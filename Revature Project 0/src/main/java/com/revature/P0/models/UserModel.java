package com.revature.P0.models;


public class UserModel {
    private String userID;
    private String username;
    private String password;
    private String role;
    private String address;
    private String city;
    private String state;

   public UserModel(){
        super();
    }


    public UserModel(String userID, String username, String password, String role,String address,String city, String state){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
    public String getUserId(){
        return userID;
    }
    public String getRole(){
        return role;
    }

    public void setId(String id) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
