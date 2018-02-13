package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public ModelAndView db(ModelAndView modelAndView) {
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
            modelAndView.setViewName("db");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("message", e.getMessage());
            return modelAndView;
        }
    }
}
