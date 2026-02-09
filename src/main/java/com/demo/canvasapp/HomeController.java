package com.demo.canvasapp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

   @GetMapping("/")
    public String home(HttpServletRequest request, Model model) throws Exception {
    
        String signedRequest = request.getParameter("signed_request");
    
        if (signedRequest == null || signedRequest.isEmpty()) {
            model.addAttribute("message", "App is running outside Salesforce Canvas.");
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

