package com.revature.P0;
enum Role{
    guest,
    admin
}

public class UserModel {
    private String userID;
    private String username;
    private String password;
    private Role role;

    UserModel(){
        super();
    }

    UserModel(String userID, String username, String password, Role role){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
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
        //role.toString();
        return role.toString();
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

    public void setRole(Role role) {
        this.role = role;
    }
}
