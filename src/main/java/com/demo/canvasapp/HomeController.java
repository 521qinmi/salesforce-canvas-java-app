package com.demo.canvasapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

    @GetMapping("/")
    //public String home(HttpServletRequest request, Model model) {
    public String home() {

        // Salesforce Canvas 会把用户信息放在请求头里
       // String signedRequest = request.getParameter("signed_request");

        //model.addAttribute("signedRequest", "test");
        return "home";
    }
}


