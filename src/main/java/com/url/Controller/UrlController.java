package com.url.Controller;

import com.url.Service.UrlService;
import com.url.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

@Controller
public class UrlController {

    @Autowired
    private UrlService service;

    // 1. Show the input form (Entry Page)
    @GetMapping("/")
    public String showForm() {
        return "index"; // Thymeleaf: templates/index.html
    }

    // 2. Process the URL and go to the Exit Page
    @PostMapping("web/shorten")
    public String shorten(@RequestParam("url") String longUrl, Model model, HttpServletRequest request) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        Url url = service.saveUrl(code, longUrl);

        // Build dynamic base URL for Railway/localhost
        String baseUrl = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            baseUrl += ":" + request.getServerPort();
        }

        model.addAttribute("shortUrl", baseUrl + "/" + url.getShortCode());
        model.addAttribute("longUrl", url.getLongUrl());
        model.addAttribute("clicks", url.getClicks());

        return "result"; // Thymeleaf: templates/result.html
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("urls", service.getAllUrls());
        return "dash"; // Thymeleaf: templates/dash.html
    }

    // 3. Handle Redirection
    @GetMapping("/{code}")
    public RedirectView redirect(@PathVariable String code) {
        String longUrl = service.getLongUrl(code);
        return new RedirectView(longUrl);
    }
}
