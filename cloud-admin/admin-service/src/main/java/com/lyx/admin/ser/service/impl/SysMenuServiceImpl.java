package com.lyx.admin.ser.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.admin.ser.entity.SysMenu;
import com.lyx.admin.ser.entity.SysRoleMenu;
import com.lyx.admin.ser.entity.req.CommonReq;
import com.lyx.admin.ser.entity.req.SaveMenuReq;
import com.lyx.admin.ser.entity.vo.SysMenuSelectVO;
import com.lyx.admin.ser.entity.vo.SysMenuVO;
import com.lyx.admin.ser.mapper.SysMenuMapper;
import com.lyx.admin.ser.service.ISysMenuService;
import com.lyx.admin.ser.service.ISysRoleMenuService;
import com.lyx.common.base.constant.GlobalConstants;
import com.lyx.common.base.result.ResultCode;
import com.lyx.common.base.utils.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author： 黎勇炫
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    private final ISysRoleMenuService roleMenuService;

    @Override
    public void deletes(List<Long> ids) {
        while (CollectionUtil.isNotEmpty(ids)) {
            this.baseMapper.deleteBatchIds(ids);
            List<SysMenu> sysMenus = lambdaQuery().in(SysMenu::getParentId, ids).select(SysMenu::getId).list();
            if (CollectionUtil.isNotEmpty(sysMenus)) {
                ids = sysMenus.stream().map(SysMenu::getId).collect(Collectors.toList());
            } else {
                ids = null;
            }
        }
    }

    @Override
    public List<SysMenuVO> loadMenus() {
        List<SysMenu> menus = lambdaQuery().eq(SysMenu::getVisible, GlobalConstants.STATUS_ON).orderByAsc(SysMenu::getSort).list();
        if (CollectionUtil.isNotEmpty(menus)) {
            List<SysMenuVO> menuVOS = flushMenuVOs(menus, GlobalConstants.ROOT_MENU_ID);
            return menuVOS;
        }
        return Collections.emptyList();
    }

    /**
     * 创建菜单
     * @param req
     */
    @Override
    public void createMenu(SaveMenuReq req) {
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(req, menu);
        save(menu);
    }

    /**
     * 更新菜单
     *
     * @param req
     */
    @Override
    public void updateMenu(SaveMenuReq req) {
        AssertUtil.notEmpty(req.getId(), ResultCode.PARAM_VALID_FAIL);
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(req,menu);
        updateById(menu);
    }

    /**
     * 批量删除菜单
     *
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {
        deletes(ids);
        lambdaUpdate().in(SysMenu::getParentId, ids).remove();
    }

    /**
     * 查询菜单selectVO
     */
    @Override
    public List<SysMenuSelectVO> getMenuSelectVO() {
        List<SysMenu> menus = lambdaQuery().orderByAsc(SysMenu::getSort).list();
        if (CollectionUtil.isNotEmpty(menus)) {
            List<SysMenuSelectVO> menuVOS = flushMenuSelectVOs(menus, GlobalConstants.ROOT_MENU_ID);
            return menuVOS;
        }
        return Collections.emptyList();
    }

    /**
     * 获取菜单详情
     *
     * @param menuId 菜单id
     */
    @Override
    public SysMenuVO info(Long menuId) {
        SysMenu menu = getById(menuId);
        AssertUtil.notEmpty(menu,ResultCode.DATA_NOT_EXIST);
        SysMenuVO vo = new SysMenuVO();
        BeanUtils.copyProperties(menu,vo);
        return vo;
    }

    /**
     * 更新角色绑定的菜单信息
     *
     * @param roleId
     * @param req
     */
    @Override
    public void updateRoleBingdingInfo(Long roleId, CommonReq req) {
        // 删除指定角色绑定的菜单
        roleMenuService.lambdaUpdate().in(SysRoleMenu::getRoleId,roleId).remove();
        // 插入新的绑定信息
        List<SysRoleMenu> sysRoleMenus = req.getIds().stream().map(id -> {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(id);
            return roleMenu;
        }).collect(Collectors.toList());

        if(CollectionUtil.isNotEmpty(sysRoleMenus)){
            roleMenuService.saveBatch(sysRoleMenus);
        }
    }

    /**
     * 查询角色绑定的菜单
     *
     * @param roleId
     */
    @Override
    public List<Long> listRoleMenu(Long roleId) {
        List<SysRoleMenu> list = roleMenuService.lambdaQuery().eq(SysRoleMenu::getRoleId, roleId).list();
        return list.stream().map(item->item.getMenuId()).collect(Collectors.toList());
    }

    /**
     * 组装菜单
     *
     * @param menus
     * @param parentId
     * @return
     */
    private List<SysMenuVO> flushMenuVOs(List<SysMenu> menus, Long parentId) {
        List<SysMenuVO> childMenus = new ArrayList<>();
        for (SysMenu sysMenu : menus) {
            if (parentId.equals(sysMenu.getParentId())) {
                SysMenuVO sysMenuVO = new SysMenuVO();
                BeanUtils.copyProperties(sysMenu, sysMenuVO);
                childMenus.add(sysMenuVO);
            }
        }
        if (CollectionUtil.isNotEmpty(childMenus)) {
            for (SysMenuVO sysMenuVO : childMenus) {
                List<SysMenuVO> childChildMenus = flushMenuVOs(menus, sysMenuVO.getId());
                sysMenuVO.setChildren(childChildMenus);
            }
            return childMenus;
        }
        return Collections.emptyList();
    }

    /**
     * 组装菜单select
     *
     * @param menus
     * @param parentId
     * @return
     */
    private List<SysMenuSelectVO> flushMenuSelectVOs(List<SysMenu> menus, Long parentId) {
        List<SysMenuSelectVO> childMenus = new ArrayList<>();
        for (SysMenu sysMenu : menus) {
            if (parentId.equals(sysMenu.getParentId())) {
                SysMenuSelectVO sysMenuVO = new SysMenuSelectVO();
                sysMenuVO.setId(sysMenu.getId());
                sysMenuVO.setLabel(sysMenu.getName());
                childMenus.add(sysMenuVO);
            }
        }
        if (CollectionUtil.isNotEmpty(childMenus)) {
            for (SysMenuSelectVO sysMenuVO : childMenus) {
                List<SysMenuSelectVO> childChildMenus = flushMenuSelectVOs(menus, sysMenuVO.getId());
                sysMenuVO.setChildren(childChildMenus);
            }
            return childMenus;
        }
        return Collections.emptyList();
    }


    @Override
    public void updateMenuStatus(Long id, int status) {
        lambdaUpdate().eq(SysMenu::getId, id).set(SysMenu::getVisible, status).update();
    }

    @Override
    public List<Long> currentUser() {
        return null;
    }

}
