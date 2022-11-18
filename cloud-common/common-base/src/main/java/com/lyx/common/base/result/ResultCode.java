package com.lyx.common.base.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 黎勇炫
 * @date 2022年10月08日 18:07
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {

    SUCCESS("000000", "成功"),
    SYSTEM_EXECUTION_ERROR("999999", "系统执行出错"),
    USERNAME_OR_PASSWORD_ERROR("A00100", "用户名或密码错误"),
    USER_NOT_EXIST("A00101", "用户不存在"),
    PARAM_VALID_FAIL("A00102","参数校验异常"),
    DATA_NOT_EXIST("A00102","数据不存在"),
    CLIENT_AUTHENTICATION_FAILED("A00212", "客户端认证失败"),
    ACCESS_UNAUTHORIZED("A00213", "未授权"),
    TOKEN_INVALID_OR_EXPIRED("A00214", "token非法或失效"),
    TOKEN_ACCESS_FORBIDDEN("A00215", "token禁止访问"),
    FLOW_LIMITING("B0210", "系统限流"),
    DEGRADATION("B0220", "系统功能降级"),
    SERVICE_NO_AUTHORITY("B0221", "服务未授权"),
    KEY_IS_DUPLICATED("C0221", "模型标识不能重复"),
    PROCESS_NOT_EXIST("C0221", "该流程不存"),
    ;
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    private String code;

    private String msg;

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"msg\":\"" + msg + '\"' +
                '}';
    }

}