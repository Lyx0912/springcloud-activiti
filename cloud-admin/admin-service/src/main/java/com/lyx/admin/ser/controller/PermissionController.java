package com.lyx.admin.ser.controller;

import com.lyx.admin.ser.entity.req.CommonReq;
import com.lyx.admin.ser.entity.req.SavePermissionReq;
import com.lyx.admin.ser.entity.vo.SysPermissionVO;
import com.lyx.admin.ser.entity.vo.SysServiceVO;
import com.lyx.admin.ser.service.ISysPermissionService;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年10月29日 21:52
 */
@RestController
@RequestMapping("/permission")
@Slf4j
@RequiredArgsConstructor
public class PermissionController {

    private final ISysPermissionService permissionService;

     /**
       * 根据菜单编号获取权限列表
       */
    @GetMapping("/list/{menuId}")
    public R listByMenu(@NotBlank(message = "菜单编号不能为空") @PathVariable Long menuId){
        List<SysPermissionVO> vos = permissionService.listByMenuId(menuId);
        return R.ok(vos);
    }

     /**
       * 新增权限
       */
    @PostMapping
    public R createPermission(@Validated @RequestBody SavePermissionReq req){
        permissionService.createPermission(req);
        return R.ok();
    }

     /**
       * 获取服务列表
       */
    @GetMapping("/services")
    public R serviceList(){
        List<SysServiceVO> vos = permissionService.getServiceList();
        return R.ok(vos);
    }

     /**
       * 更新权限
       */
    @PutMapping
    public R updatePermission(@Validated @RequestBody SavePermissionReq req){
        permissionService.updatePermission(req);
        return R.ok();
    }

    /**
     * 删除权限
     */
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable List<Long> ids){
        permissionService.deletePermission(ids);
        return R.ok();
    }

    /**
     * 查询角色绑定的权限
     */
    @GetMapping("/role/{roleId}")
    public R<List<Long>> listRolePermission(@PathVariable Long roleId){
        List<Long> permissions = permissionService.listRolePermission(roleId);
        return R.ok(permissions);
    }

    /**
     * 更新角色绑定的权限
     */
    @PutMapping("/role/{roleId}")
    public R updateRoleBingdingInfo(@PathVariable Long roleId,@RequestBody CommonReq req){
        permissionService.updateRoleBingdingInfo(roleId,req);
        return R.ok();
    }
}
