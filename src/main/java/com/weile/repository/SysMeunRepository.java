package com.weile.repository;

import com.weile.domain.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: xwl
 * @Date: 2024/5/25 20:22
 * @Description:
 **/
@Repository
public interface SysMeunRepository extends JpaRepository<SysMenu,Long> {
}
