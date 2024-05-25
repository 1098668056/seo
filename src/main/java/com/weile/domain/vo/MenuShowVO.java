package com.weile.domain.vo;

import com.weile.domain.SysMenu;
import lombok.Data;

import java.util.List;

@Data
public class MenuShowVO extends SysMenu{

    private List<SysMenu> subMenus; // 对应的二级菜单
}
