package com.itguigu.api;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ApiController {

    @Autowired
    private ServletContext servletContext;

    public void data(HttpServletResponse response,
                     HttpServletRequest request,
                     HttpSession session) {

        ServletContext servletContext = request.getServletContext();

    }
}
