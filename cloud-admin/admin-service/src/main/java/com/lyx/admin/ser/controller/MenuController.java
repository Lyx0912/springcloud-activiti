package com.lyx.admin.ser.controller;

import com.lyx.admin.ser.entity.req.SaveMenuReq;
import com.lyx.admin.ser.entity.vo.SysMenuSelectVO;
import com.lyx.admin.ser.entity.vo.SysMenuVO;
import com.lyx.admin.ser.service.ISysMenuService;
import com.lyx.admin.ser.service.ISysPermissionService;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.MatchesPattern;
import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年10月29日 18:17
 */
@RestController
@RequestMapping("/menu")
@Slf4j
@RequiredArgsConstructor
public class MenuController {
    private final ISysMenuService menuService;

    @GetMapping("/list")
    public R buildMenu(){
        List<SysMenuVO> vos = menuService.loadMenus();
        return R.ok(vos);
    }

     /**
       * 创建菜单
       */
    @PostMapping
    public R create(@Validated @RequestBody SaveMenuReq req){
        menuService.createMenu(req);
        return R.ok();
    }

     /**
       * 批量删除菜单
       */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable List<Long> ids){
        menuService.deleteBatch(ids);
        return R.ok();
    }

    @GetMapping("tree")
    public R buildTree(){
        List<SysMenuVO> vos = menuService.loadMenus();
        return R.ok(vos);
    }

     /**
       * 更新菜单
       */
    @PutMapping
    public R updateMenu(@Validated @RequestBody SaveMenuReq req){
        menuService.updateMenu(req);
        return R.ok();
    }

     /**
       * 菜单列表查询
       */
    @GetMapping("/select")
    public R select(){
        List<SysMenuSelectVO> selectVOS = menuService.getMenuSelectVO();
        return R.ok(selectVOS);
    }

     /**
       * 获取菜单详情
       */
    @GetMapping("/info/{menuId}")
    public R info(@NotBlank(message = "菜单id不能为空") @PathVariable Long menuId){
        SysMenuVO vo = menuService.info(menuId);
        return R.ok(vo);
    }

}
