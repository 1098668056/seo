package com.weile.repository;

import com.weile.domain.KeyWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/17 10:30
 * @Description:
 **/
@Repository
public interface KeyWordsRepository extends JpaRepository<KeyWords,Long> {

    int countByUseCountEquals(Integer useCount);


    List<KeyWords> findTop8ByKeyNameContainingOrderByUseCountAsc(String keyName);

}
