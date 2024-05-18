package com.weile.repository;

import com.weile.domain.SeoHtml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/12 15:47
 * @Description:
 **/
@Repository
public interface SeoHtmlRepository extends JpaRepository<SeoHtml,Long> {
    /**
     * 按照标题取前5条数据
     * @return
     */

    List<SeoHtml> findFirst5ByOrderByTitleDesc();


}
