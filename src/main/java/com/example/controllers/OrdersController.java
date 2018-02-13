package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class OrdersController {

    @Autowired
    private DataSource dataSource;

    String db(Map<String, Object> model) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
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

            model.put("records", output);
            return "db";
        } catch (Exception e) {
            model.put("message", e.getMessage());
            return "error";
        }
    }
}
