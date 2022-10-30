package com.lyx.admin.ser.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lyx.common.web.utils.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 黎勇炫
 * @date 2022年10月08日 18:35
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增填充创建时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", () -> new Date(), Date.class);
        this.strictInsertFill(metaObject, "updateTime", () -> new Date(), Date.class);
        this.strictInsertFill(metaObject, "createBy", () -> UserContext.getCurrentUserId(), Long.class);
        this.strictInsertFill(metaObject, "updateBy", () -> UserContext.getCurrentUserId(), Long.class);
        System.out.println(metaObject);
    }

    /**
     * 更新填充更新时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", () -> new Date(), Date.class);
        this.strictUpdateFill(metaObject, "updateBy", () -> UserContext.getCurrentUserId(), Long.class);
    }

}