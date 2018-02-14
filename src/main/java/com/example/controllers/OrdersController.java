package com.example.controllers;

import com.example.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView orders(ModelAndView modelAndView) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");

            ArrayList<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setSurname(rs.getString("surname"));
                order.setName(rs.getString("name"));
                order.setPhone(rs.getString("phone"));
                order.setNote(rs.getString("note"));
                order.setComplete(rs.getBoolean("complete"));
                order.setCreateDate(rs.getTimestamp("createtime"));
                orders.add(order);
            }
            modelAndView = new ModelAndView();
            modelAndView.addObject("orders", orders);
            modelAndView.setViewName("orders");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("message", e.getMessage());
            return modelAndView;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST)
    ModelAndView completeOrder(ModelAndView model,
                               @RequestParam(value = "id")int id,
                               @RequestParam(value = "isComplete")String isComplete) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            String query = "update orders set complete = " + isComplete + " where id = " + id + " ;";
            stmt.executeQuery(query);
            model.addObject("result", "success");
            return model;
        } catch (Exception e) {
            model.addObject(e);
            return model;
        }
    }
}
