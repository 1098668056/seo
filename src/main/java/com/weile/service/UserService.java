package com.weile.service;

import com.weile.domain.vo.MenuShowVO;

import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/25 20:53
 * @Description:
 **/
public interface UserService {
    /**
     * 获取菜单列表
     * @return
     */
    List<MenuShowVO> getMenuList();
}
