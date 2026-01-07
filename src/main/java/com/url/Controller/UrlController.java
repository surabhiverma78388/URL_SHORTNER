package com.url.Controller;

import com.url.Service.UrlService;
import com.url.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Changed from RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller // Changed to support JSP views
public class UrlController {

    @Autowired
    private UrlService service;

    // 1. Show the input form (Entry Page)
    @GetMapping("/")
    public String showForm() {
        return "index"; // Looks for index.jsp
    }

    // 2. Process the URL and go to the Exit Page
    @PostMapping("web/shorten")
    public String shorten(@RequestParam("url") String longUrl, Model model) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        Url url = service.saveUrl(code, longUrl);
        
        // Pass data to result.jsp
        model.addAttribute("shortUrl", "http://localhost:8081/" + url.getShortCode());
        model.addAttribute("longUrl", url.getLongUrl());
        model.addAttribute("clicks", url.getClicks());
        
        return "result"; // Looks for result.jsp
    }
    @GetMapping("/dashboard")
public String showDashboard(Model model) {
    model.addAttribute("urls", service.getAllUrls()); // Saare URLs list pass karein
    return "dash"; // dash.jsp load hoga
}
    // 3. Handle Redirection (Already works with RedirectView)
    @GetMapping("/{code}")
    public RedirectView redirect(@PathVariable String code) {
        String longUrl = service.getLongUrl(code);
        return new RedirectView(longUrl);
    }
}