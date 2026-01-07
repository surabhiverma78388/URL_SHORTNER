package com.url.Service;
import com.url.model.Url;
import com.url.Repository.UrlRepository;
import com.url.exception.UrlNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UrlService {

    @Autowired
    private UrlRepository repo;
    public List<Url> getAllUrls() {
    return repo.findAll(); // JpaRepository se saare records mil jayenge
}
    public Url saveUrl(String shortCode, String longUrl) {
    if (longUrl != null && longUrl.toLowerCase().startsWith("www.")) {
        longUrl = "https://" + longUrl;
    }
        
    // Check if URL already exists
    Url existing = repo.findByLongUrl(longUrl);
    if (existing != null) {
        return existing; // Return existing shortCode
    }
    

    Url url = new Url();
    url.setShortCode(shortCode);
    url.setLongUrl(longUrl);
    return repo.save(url);
}
    public String getLongUrl(String code) {
    Url url = repo.findByShortCode(code);
    if (url == null) {
        throw new UrlNotFoundException(code);
    }
    // Increment click count
    url.setClicks(url.getClicks() + 1);
    repo.save(url);
    return url.getLongUrl();
}
    public Url getUrlByCode(String code) {
    Url url = repo.findByShortCode(code);
    if (url == null) {
        throw new UrlNotFoundException(code);
    }
    return url;
}}

