package com.weile.repository;

import com.weile.domain.SeoHtml;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/12 15:47
 * @Description:
 **/

public interface SeoHtmlRepository extends JpaRepository<SeoHtml,Long> {
    @Query(value = "SELECT id, title, description, keywords, content, url, fileName FROM SeoHtml")
    List<SeoHtml> getAllSeoHtml(Pageable pageable);
    @Query(value = "SELECT id, title, description, keywords, content, url, file_name FROM html ORDER BY RAND() LIMIT 5",nativeQuery = true)
    List<SeoHtml> getSeoRandom();
}
