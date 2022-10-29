package com.lyx.admin.ser.controller;

import com.lyx.admin.ser.entity.SysRole;
import com.lyx.admin.ser.entity.vo.SysRoleSelectVO;
import com.lyx.admin.ser.entity.vo.SysRoleVO;
import com.lyx.admin.ser.service.ISysRoleService;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
