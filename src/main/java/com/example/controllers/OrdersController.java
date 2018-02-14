package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private DataSource dataSource;

//    @PreAuthorize("#oauth2.hasScope('orders')")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView orders(ModelAndView modelAndView) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                String order = rs.getString("surname");
                order += ", ";
                order += rs.getString("name");
                order += ", ";
                order += rs.getString("phone");
                order += ", ";
                order += rs.getString("note");
                order += ", ";
                order += rs.getTimestamp("createtime");
                output.add("Заказ: " + order);
            }
            modelAndView = new ModelAndView();
            modelAndView.addObject("records", output);
            modelAndView.setViewName("orders");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("message", e.getMessage());
            return modelAndView;
        }
    }
}
