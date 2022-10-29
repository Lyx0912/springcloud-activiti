package com.lyx.admin.ser.listener;

import com.lyx.admin.ser.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 黎勇炫
 * @date 2022年10月10日 17:16
 */
@Component
public class BootStartListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private ISysPermissionService iSysPermissionService;

     /**
       * 监听springboot的启动，容器启动完毕后自动调用refreshPermRolesRules刷新权限角色
       */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationReadyEvent){
            iSysPermissionService.refreshPermRolesRules();
        }
    }
}
