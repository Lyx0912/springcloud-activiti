package com.lyx.admin.ser.controller;

import com.lyx.admin.ser.entity.SysRole;
import com.lyx.admin.ser.entity.req.RoleListPageReq;
import com.lyx.admin.ser.entity.req.SaveSysRoleReq;
import com.lyx.admin.ser.entity.vo.SysRoleSelectVO;
import com.lyx.admin.ser.entity.vo.SysRoleVO;
import com.lyx.admin.ser.service.ISysRoleService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年10月27日 19:00
 */
@RequestMapping("/role")
@RestController
@Slf4j
@RequiredArgsConstructor
public class RoleController {

    private final ISysRoleService roleService;

     /**
       * 查询角色列表
       */
    @GetMapping
    public R roleList(){
        List<SysRoleVO> res = roleService.listRoleVO();
        return R.ok(res);
    }

     /**
       * 分页查询角色
       */
    @GetMapping("/list")
    public R list(@RequestBody RoleListPageReq req){
        PageUtils<SysRoleVO> vos = roleService.listPage(req);
        return R.ok(vos);
    }

     /**
       * 新增角色
       */
    @PostMapping
    public R createRole(@RequestBody SaveSysRoleReq req){
        roleService.createRole(req);
        return R.ok();
    }

     /**
       * 更新角色信息
       */
    @PutMapping
    public R updateRole(@Validated @RequestBody SaveSysRoleReq req){
        roleService.updateRole(req);
        return R.ok();
    }

     /**
       * 删除角色
       */
    @DeleteMapping("/{ids}")
    public R deleteRole(@PathVariable List<Long> ids){
        roleService.removeRole(ids);
        return R.ok();
    }
}
