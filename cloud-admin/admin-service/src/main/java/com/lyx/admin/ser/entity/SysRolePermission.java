package com.lyx.admin.ser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author： 黎勇炫
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRolePermission {
    private Long permissionId;
    private Long roleId;
}
