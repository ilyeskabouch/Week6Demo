/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author ilyes
 */
public class UserDB {
    private String path;

    public UserDB(String path) {
        this.path = path;
    }
    public User getUser(String username) throws Exception {
        User user = null;
        
        BufferedReader br = new BufferedReader(new FileReader( new File(path + "/users.txt")));
        String line = br.readLine();
        
        while (line != null) {
            String[] parts = line.split(",");
            if (username.equals(parts[0])) {
                user = new User(parts[0],parts[1],parts[2],parts[3]);
            }
            line = br.readLine();
        }
        br.close();
        
        return user;
    }
    
    public List<User> getAllUsers() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(new File(path + "/users.txt")));
        String line = br.readLine();
        
        while (line != null) {
            String[] parts = line.split(",");
            User user = new User(parts[0],parts[1],parts[2],parts[3]);
            users.add(user);
            line = br.readLine();
        }
        br.close();
        
        return users;
    }
    
    public List<User> getActiveUsers(int offset, int count) throws Exception {
        ArrayList<User> users = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(new File(path + "/users.txt")));
        String line = br.readLine();
        
        int i = 0;
        while (line != null) {
            String[] parts = line.split(",");
            if (parts[4].equals("0")) {
                i++;
                
                if (i <= offset) {
                    line = br.readLine();
                    continue;
                }
                
                if (i > offset + count) {
                    break;
                }
                
                User user = new User(parts[0],parts[1],parts[2],parts[3]);
                users.add(user);
            }
            
            line = br.readLine();
        }
        br.close();
        
        return users;
    }
}
