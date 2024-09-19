package com.majortomdev.historybank.controllers;

import com.majortomdev.historybank.dto.UrlDto;
import com.majortomdev.historybank.entities.UrlObj;
import com.majortomdev.historybank.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/save")
    public ResponseEntity<UrlObj> saveUrl(@RequestBody UrlDto urlDto, Principal principal) {
        UrlObj savedUrl = urlService.saveUrl(new UrlObj(urlDto.getUrl()), principal.getName());
        return ResponseEntity.ok(savedUrl);
    }

    @GetMapping
    public ResponseEntity<List<UrlObj>> getAllUrls(Principal principal) {
        List<UrlObj> urls = urlService.getUrlsForUser(principal.getName());
        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable int id, Principal principal) {
        urlService.deleteUrl(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}
