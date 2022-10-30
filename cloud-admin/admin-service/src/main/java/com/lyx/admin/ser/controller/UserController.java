package com.lyx.admin.ser.controller;

import com.lyx.admin.dto.UserAuthDTO;
import com.lyx.admin.ser.entity.SysMenu;
import com.lyx.admin.ser.entity.SysUser;
import com.lyx.admin.ser.entity.req.SaveUserReq;
import com.lyx.admin.ser.entity.req.UserListPageReq;
import com.lyx.admin.ser.entity.vo.SysMenuSelectVO;
import com.lyx.admin.ser.entity.vo.SysUserVO;
import com.lyx.admin.ser.service.ISysUserService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author： AI码师 关注公众号"AI码师"获取完整源码
 * @date： 2021/11/24
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final ISysUserService sysUserService;

    @GetMapping("/current")
    public R currentUser(){
        SysUserVO vo = sysUserService.currentUserInfo();
        return R.ok(vo);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getByUserName/{username}")
    public R<UserAuthDTO> getUserByUsername(@NotBlank(message = "username can not be null!") @PathVariable String username) {
        UserAuthDTO user = sysUserService.getByUsername(username);
        return R.ok(user);
    }

     /**
       * 创建用户(默认密码123456789)
       */
    @PostMapping
    public R createUser(@Validated  @RequestBody SaveUserReq req){
        sysUserService.createUser(req);
        return R.ok();
    }

     /**
       * 通过用户id获取用户详细信息
       */
    @GetMapping("/{userId}")
    public R userDetail(@NotBlank(message = "id can not be null!") @PathVariable Long userId){
        SysUserVO vo = sysUserService.userDetail(userId);
        return R.ok(vo);
    }

     /**
       * 更新用户信息
       */
    @PutMapping("/{userId}")
    public R update(@Validated @RequestBody SaveUserReq req,@PathVariable Long userId){
        sysUserService.updateUserInfo(req,userId);
        return R.ok();
    }

     /**
       * 批量刪除用戶
       */
    @DeleteMapping("/{userIds}")
    public R deleteBatch(@Size(min = 1,message = "用户编号不能为空") @PathVariable List<Long> userIds){
        sysUserService.removeByIds(userIds);
        return R.ok();
    }

     /**
       * 分頁查詢用戶
       */
    @GetMapping("/list")
    public R list(UserListPageReq req){
        PageUtils<SysUserVO> page= sysUserService.pageUser(req);
        return R.ok(page);
    }

    @PatchMapping("/{userId}")
    public R updateStatus(@NotBlank(message = "用户编号不能为空") @PathVariable Long userId,@NotBlank(message = "状态不能为空") @RequestParam Integer status){
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setStatus(status);
        sysUserService.getBaseMapper().updateById(sysUser);
        return R.ok();
    }

}
