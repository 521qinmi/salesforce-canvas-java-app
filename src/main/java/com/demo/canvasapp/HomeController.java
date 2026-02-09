package com.demo.canvasapp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(HttpServletRequest request, Model model) throws Exception {

        String signedRequest = request.getParameter("signed_request");

        if (signedRequest == null || signedRequest.isEmpty()) {
            model.addAttribute("message", "App running outside Salesforce");
            return "home";
        }

        String[] parts = signedRequest.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(payload, Map.class);

        model.addAttribute("canvasData", data);
        return "home";
    }
}


