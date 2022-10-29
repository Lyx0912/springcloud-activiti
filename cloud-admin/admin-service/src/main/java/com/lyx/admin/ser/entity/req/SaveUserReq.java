package com.lyx.admin.ser.entity.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author： 黎勇炫
 */
@Data
public class SaveUserReq {

    private Long id;

    @NotBlank(message = "账号不能为空")
    private String username;

    @NotBlank(message = "用户名不能为空")
    private String nickname;

    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotNull(message = "性别不能为空")
    private Integer gender;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotNull(message = "状态不能为空")
    private Integer status;

    @Size(min = 1,message = "角色不能为空")
    private List<Long> roleIds;

}
