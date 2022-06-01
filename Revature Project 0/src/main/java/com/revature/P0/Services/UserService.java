package com.revature.P0.Services;

import com.revature.P0.DataBase.UserDAO;
import com.revature.P0.Utilities.annotations.Inject;
import com.revature.P0.Utilities.custom_exceptions.InvalidStateException;
import com.revature.P0.Utilities.custom_exceptions.InvalidUserException;
import com.revature.P0.models.AddressModel;
import com.revature.P0.models.Game;
import com.revature.P0.models.UserModel;

import java.util.List;

public class UserService {
    @Inject
    private final UserDAO userDAO;
    @Inject
    public UserService(UserDAO userDAO){this.userDAO = userDAO;}

    public UserModel login(String username, String password){
        UserModel user = new UserModel();
        List<UserModel> users = userDAO.getAll();

        for(UserModel u : users){
            if (u.getUsername().equals(username)){
                user.setId(u.getUserId());
                user.setUsername(u.getUsername());
                user.setRole(u.getRole());
                if(u.getPassword().equals(password)){
                    user.setPassword(u.getPassword());
                    break;
                }
            }
            if (u.getPassword().equals(password)){
                user.setPassword(u.getPassword());
                break;
            }
        }
        return isValidCredentials(user);

    }

    public void register(UserModel user){
        userDAO.save(user);
    }

    public String getDeeID(UserModel user){userDAO.getDatId(user);
    return user.getUserId();}


    public boolean isNotDuplicateUsername(String username){
        List<String> usernames = userDAO.getAllUsernames();
        if (usernames.contains(username)) throw new InvalidUserException("Username is already in use");
        return true;
    }
    public boolean isUserValid (String username){
        if (username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;
        throw new InvalidUserException("Invalid username. Username must to be 8-20 characters.");

    }

    public boolean isStateValid(String state){
        if (state.matches("^(?:(A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEHJMVY]|O[HKR]|P[AR]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY]))$")) return true;
        else return false;


    }

    private UserModel isValidCredentials(UserModel user){
        if(user.getUsername() == null && user.getPassword() == null)
            throw new InvalidUserException("Incorrect username and password");
        else if (user.getUsername() == null) throw new InvalidUserException("Incorrect username");
        else if (user.getPassword() == null) throw new InvalidUserException("Incorrect password");


        return user;
    }


    public boolean isPassValid(String password){
        return password.matches("^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$");

    }
    public UserModel getUserById(String id){
       return userDAO.getById(id);
    }


}
