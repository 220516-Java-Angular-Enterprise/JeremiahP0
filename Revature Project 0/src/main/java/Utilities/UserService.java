package Utilities;

public class UserService {
    public boolean isUserValid(String username){

        return username.matches("^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$");
    }
    public boolean isPassValid(String password){
        return password.matches("^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$");

    }
/*
    public boolean doesUserExist(String username){
        return username.matches();
    }
    public boolean doesPassExist(String password){
        return password.matches();

    }
    */


}
