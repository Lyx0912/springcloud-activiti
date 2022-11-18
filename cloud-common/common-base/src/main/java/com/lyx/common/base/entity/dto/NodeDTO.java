package com.lyx.common.base.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 黎勇炫
 * @date 2022年11月07日 22:23
 */
@Data
public class NodeDTO implements Serializable {

    /**
     * 当前节点名称
     */
    private String currentNode;
    /**
     * 当前审批角色
     */
    private String currentRole;
}
