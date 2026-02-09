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

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

   @RequestMapping(value = "/canvas", method = {RequestMethod.GET, RequestMethod.POST})
public String canvas(HttpServletRequest request, Model model) {

    System.out.println("=== Canvas Endpoint Hit ===");
    System.out.println("METHOD = " + request.getMethod());

    try {
        String signedRequest = request.getParameter("signed_request");
        System.out.println("signed_request = " + signedRequest);

        if (signedRequest != null && !signedRequest.isEmpty()) {
            String[] parts = signedRequest.split("\\.");

            String payload = new String(
                java.util.Base64.getUrlDecoder().decode(parts[1]),
                java.nio.charset.StandardCharsets.UTF_8
            );

            System.out.println("Payload = " + payload);

            com.fasterxml.jackson.databind.ObjectMapper mapper =
                new com.fasterxml.jackson.databind.ObjectMapper();

            java.util.Map<String, Object> data =
                mapper.readValue(payload, java.util.Map.class);

            model.addAttribute("canvasData", data);
            model.addAttribute("message", "Loaded inside Salesforce ✅");
        } else {
            model.addAttribute("message", "Opened outside Salesforce");
        }

    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("message", "Error but still returning page: " + e.getMessage());
    }

    return "home"; // 永远返回页面，绝不抛错
}
   // 浏览器直接访问时用（方便调试）
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "App is running (direct access)");
        return "home";
    }
}









