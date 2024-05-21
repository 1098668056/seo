package com.weile.repository;

import com.weile.domain.HtmlBehavior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: xwl
 * @Date: 2024/5/19 15:25
 * @Description:
 **/
@Repository
public interface HtmlBehaviorRepository extends JpaRepository<HtmlBehavior,Long> {
    /**
     * 根据id统计条数
     * @param htmlId
     * @return
     */
    Long countByHtmlId(Long htmlId);
}
