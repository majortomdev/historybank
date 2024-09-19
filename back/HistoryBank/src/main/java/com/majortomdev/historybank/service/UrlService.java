package com.majortomdev.historybank.service;

import com.majortomdev.historybank.entities.UrlObj;
import com.majortomdev.historybank.entities.User;
import com.majortomdev.historybank.repository.UrlRepository;
import com.majortomdev.historybank.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    public UrlService(UrlRepository urlRepository, UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    public UrlObj saveUrl(UrlObj urlObj, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        UrlObj newUrl = new UrlObj(urlObj.getUrl(), user);
        return urlRepository.save(newUrl);
    }

    public List<UrlObj> getUrlsForUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return urlRepository.findByUserId(user.getId());
    }

    public void deleteUrl(int urlId, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        UrlObj url = urlRepository.findById(urlId)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        if (url.getUser().getId() != user.getId()) {
            throw new RuntimeException("You are not authorized to delete this URL");
        }

        urlRepository.delete(url);
    }
}
