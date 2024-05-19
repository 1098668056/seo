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
    /**
     * 根据关键词进行模糊匹配
     * @param keyName
     * @return
     */
    List<KeyWords> findByKeyNameLike(String keyName);
    /**
     * 根据关键词进行模糊匹配并取前三个词汇
     * @param keyName
     * @return
     */
    @Query(value = "select keyName from KeyWords where keyName like %:keyName% order by keyName desc")
    List<String> findFirstByKeyName (@Param("keyName") String keyName);

    int countByUseCountEquals(Integer useCount);
}
