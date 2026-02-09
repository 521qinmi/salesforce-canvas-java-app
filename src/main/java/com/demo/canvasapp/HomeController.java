package com.demo.canvasapp;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        //model.addAttribute("signed_request", "Canvas App is running 🚀");
        return "home";
        /*String signedRequest = request.getParameter("signed_request");

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
        */
    }
}



