package com.weile.repository;

import com.weile.domain.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author: xwl
 * @Date: 2024/6/16 14:55
 * @Description:
 **/
@Repository
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo,Long> {
   WebSiteInfo  findFirst1ByHost(String host);
}
