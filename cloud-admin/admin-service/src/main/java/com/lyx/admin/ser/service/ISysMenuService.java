package com.lyx.admin.ser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.admin.ser.entity.SysMenu;
import com.lyx.admin.ser.entity.req.CommonReq;
import com.lyx.admin.ser.entity.req.SaveMenuReq;
import com.lyx.admin.ser.entity.vo.SysMenuSelectVO;
import com.lyx.admin.ser.entity.vo.SysMenuVO;

import java.util.List;

/**
 * @author： 黎勇炫
 */
public interface ISysMenuService extends IService<SysMenu> {
//    /**
//     * 加载数据表格菜单
//     * @param
//     */
//    List<SysMenuVO> loadMenus();
//
//    /**
//     * 创建菜单
//     * @param req
//     */
//    void createMenu(SaveMenuReq req);
//
//    /**
//     * 菜单树
//     * @return
//     */
//    List<SysMenuVO> listTree();
//
//    /**
//     * 更新菜单
//     * @param req
//     */
//    void updateMenu(SaveMenuReq req);

    /**
     * 批量删除
     * @param ids
     */
    void deletes(List<Long> ids);

//    /**
//     * 获取菜单select
//     * @return
//     */
//    List<SysMenuSelectVO> select(int status);
//
//    /**
//     * 获取菜单详情
//     * @param id
//     * @return
//     */
//    SysMenuVO getDetail(Long id);
//
//    /**
//     * 查询角色绑定的菜单
//     * @param roleId
//     * @return
//     */
//    List<Long> listRoleMenu(Long roleId);
//
//    /**
//     * 更新角色绑定的菜单
//     * @param roleId
//     * @param req
//     */
//    void updateRoleMenu(Long roleId, CommonReq req);

    void updateMenuStatus(Long id, int status);

    List<Long> currentUser();

     /**
       * 构建菜单树
       */
    List<SysMenuVO> loadMenus();

     /**
       * 创建菜单
       */
    void createMenu(SaveMenuReq req);

     /**
       * 更新菜单
       */
    void updateMenu(SaveMenuReq req);

     /**
       * 批量删除菜单
       */
    void deleteBatch(List<Long> ids);

     /**
       * 查询菜单selectVO
       */
    List<SysMenuSelectVO> getMenuSelectVO();

     /**
       * 获取菜单详情
       */
    SysMenuVO info(Long menuId);

     /**
       * 更新角色绑定的菜单信息
       */
    void updateRoleBingdingInfo(Long roleId, CommonReq req);
}
