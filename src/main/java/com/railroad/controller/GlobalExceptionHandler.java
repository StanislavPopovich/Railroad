package com.railroad.controller;

import com.railroad.exceptions.RailroadDaoException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public String handleSqlException() {
        return "accessDeniedPage";
    }

    @ExceptionHandler(RailroadDaoException.class)
    public String handleDaoException(Model model) {
        model.addAttribute("message", true);
        return "clientMessagePage";
    }




}
