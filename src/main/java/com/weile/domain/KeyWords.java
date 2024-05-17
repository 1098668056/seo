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
    private String keys;
    private int useCount;
    private int score;
}
