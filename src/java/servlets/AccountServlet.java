/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author ilyes
 */
public class AccountServlet extends HttpServlet {

    private final int PAGE_SIZE = 10;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        if (username != null && !username.isEmpty()) {
            displayUserInfo(request, response);
        } else {
            displayAllUsers(request, response);
        }
    }

    private void displayAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> accounts = null;
        int page = 1;
        
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            //TO-DO
        }
        
        AccountService accountService = new AccountService(getServletContext().getRealPath("/WEB-INF/")); /// 10:38 / 21:05 part 1 video
        try {
            accounts = (ArrayList<User>) accountService.getAccounts(page, PAGE_SIZE);
        } catch (Exception e) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        request.setAttribute("accounts", accounts);
        request.setAttribute("page", page);
        getServletContext().getRequestDispatcher("/WEB-INF/accounts.jsp").forward(request,response);
    }
    
    public void displayUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        
        try {
            String username = request.getParameter("username");
            AccountService userService = new AccountService(getServletContext().getRealPath("/WEB-INF/"));
            
            user = userService.getAccount(username);
        } catch(Exception e) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        request.setAttribute("account", user);
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);
    }


}
