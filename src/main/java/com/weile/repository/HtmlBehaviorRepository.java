package com.weile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: xwl
 * @Date: 2024/5/19 15:25
 * @Description:
 **/
@Repository
public interface HtmlBehaviorRepository extends JpaRepository<com.weile.domain.HtmlBehavior,Long> {
}
