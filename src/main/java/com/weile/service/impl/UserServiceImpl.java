package com.weile.service.impl;

import com.weile.domain.SysMenu;
import com.weile.domain.vo.MenuShowVO;
import com.weile.repository.SysMeunRepository;
import com.weile.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xwl
 * @Date: 2024/5/25 20:54
 * @Description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private SysMeunRepository sysMeunRepository;
    /**
     * 获取菜单列表
     *
     * @return
     */
    @Override
    public List<MenuShowVO> getMenuList() {
        List<SysMenu> list = sysMeunRepository.findAll();
        // 循环找出所有的一级菜单
        List<MenuShowVO> collect = list.stream().filter(item -> {
            return item.getParentId() == -1;
        }).map(item -> {
            MenuShowVO showVO = new MenuShowVO();
            // 一级菜单属性的拷贝
            BeanUtils.copyProperties(item, showVO);
            List<SysMenu> subList = new ArrayList<>();
            // 根据一级菜单找到对应的二级菜单信息
            for (SysMenu menu : list) {
                if (menu.getParentId() == item.getId().intValue()) {
                    subList.add(menu);
                }
            }
            showVO.setSubMenus(subList);
            return showVO;
        }).collect(Collectors.toList());
        return collect;
    }
}
