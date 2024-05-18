package com.weile.repository;

import com.weile.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: xwl
 * @Date: 2024/5/12 11:42
 * @Description:
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
