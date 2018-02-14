//package com.example.controllers;
//
//import com.example.Main;
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/")
//public class MainController {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @RequestMapping(method = RequestMethod.GET)
//    ModelAndView index() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    ModelAndView order(ModelAndView model,
//                       @RequestParam(value = "surname")String recipient_surname,
//                       @RequestParam(value = "name")String recipient_name,
//                       @RequestParam(value = "phone")String recipient_phone,
//                       @RequestParam(value = "note")String recipient_note) {
//        try (Connection connection = dataSource.getConnection()) {
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate("INSERT INTO orders(surname, name, phone, note, complete, createtime) VALUES ('" +
//                    recipient_surname + "', '" + recipient_name + "', '" + recipient_phone + "', '" +
//                    recipient_note + "', false, now())");
//            model = new ModelAndView(new MappingJackson2JsonView());
//            model.addObject("result", "success");
//            return model;
//        } catch (Exception e) {
//            model.addObject(e);
//            return model;
//        }
//    }
//
//}
