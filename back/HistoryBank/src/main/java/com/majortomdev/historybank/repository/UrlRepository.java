package com.majortomdev.historybank.repository;

import com.majortomdev.historybank.entities.UrlObj;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UrlRepository extends JpaRepository<UrlObj, Integer> {
    List<UrlObj> findByUserId(Integer userId);
}
