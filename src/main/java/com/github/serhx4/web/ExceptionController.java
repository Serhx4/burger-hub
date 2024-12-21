package com.github.serhx4.web;

import com.github.serhx4.exception.NoItemFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.github.serhx4.web")
public class ExceptionController {

    @ExceptionHandler(NoItemFoundException.class)
    public String handleNoItemFoundException(Model model, NoItemFoundException exception) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }
}
