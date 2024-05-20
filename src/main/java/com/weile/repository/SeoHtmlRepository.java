package com.weile.repository;

import com.weile.domain.SeoHtml;
import com.weile.domain.vo.SeoHtmlVO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    /**
     * 获取前一篇文章
     * @return
     */

    Optional<SeoHtml> findFirst1ByIdBeforeOrderByIdDesc(Long id);
    /**
     * 获取后一篇文章
     * @return
     */

    Optional<SeoHtml> findFirst1ByIdAfterOrderById(Long id);

}
