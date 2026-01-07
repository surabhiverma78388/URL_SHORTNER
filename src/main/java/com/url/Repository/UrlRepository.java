package com.url.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.url.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortCode(String shortCode);
    Url findByLongUrl(String longUrl);
}
