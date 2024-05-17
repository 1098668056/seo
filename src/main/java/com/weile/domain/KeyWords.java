package com.weile.domain;



import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: xwl
 * @Date: 2024/5/17 10:26
 * @Description:
 **/
@Entity
@Table(name = "keywords")
public class KeyWords implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyName;
    private int useCount;
    private int score;

    public KeyWords() {
    }

    public KeyWords(Long id, String keyName, int useCount, int score) {
        this.id = id;
        this.keyName = keyName;
        this.useCount = useCount;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public int getUseCount() {
        return useCount;
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
