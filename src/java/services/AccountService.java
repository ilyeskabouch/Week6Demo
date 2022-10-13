/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import database.UserDB;
import models.User;
/**
 *
 * @author ilyes
 */
public class AccountService {
    private UserDB userDB;
    private final String path;
    
    public AccountService(String path) {
        this.path = path;
    }
    
    public List<User> getAccounts(int page, int pageSize) throws Exception {
        userDB = new UserDB(path);
        List<User> users = userDB.getActiveUsers( (page - 1) *pageSize , pageSize);
        return users;
    }
    
    public User getAccount(String username) throws Exception {
        userDB = new UserDB(path);
        return(userDB.getUser(username));
    }
}
