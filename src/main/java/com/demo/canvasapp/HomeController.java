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

   @RequestMapping(value = "/canvas", method = RequestMethod.POST)
    public String home(
           HttpServletRequest request,
            @RequestParam(value = "signed_request", required = false) String signedRequest,
            Model model) throws Exception {

       model.addAttribute("requestmethod", request.getMethod());
        if (signedRequest == null) {
            model.addAttribute("message", "No signed_request (Not opened inside Salesforce)");
            return "home";
        }

        String[] parts = signedRequest.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(payload, Map.class);

        model.addAttribute("canvasData", data);
        return "home";
    }
   // 浏览器直接访问时用（方便调试）
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "App is running (direct access)");
        return "home";
    }
}







