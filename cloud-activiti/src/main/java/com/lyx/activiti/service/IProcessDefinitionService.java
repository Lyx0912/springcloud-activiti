package com.lyx.activiti.service;

import com.lyx.activiti.entity.req.ProcessListPageReq;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.web.feign.FeignConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 15:55
 */
public interface IProcessDefinitionService {

     /**
       * 部署流程
       */
    void deploymentProcess(String name, MultipartFile file);

     /**
       * 分页查询部署和定义信息
       */
     PageUtils processList(ProcessListPageReq req);

      /**
        * 挂起/激活流程
        */
    void changeStatus(String defId);

    /**
     * 根据id删除流程定义
     * @param id
     * @return void
     * @author 黎勇炫
     * @create 2022/11/4
     * @email 1677685900@qq.com
     */
    void removeDefById(String id);

     /**
       * 下载流程xml文件
       */
    InputStream downXml(String id);

     /**
       * 启动流程
       */
     String startProcessInstance(String defId, Long busId, Map<String,Object> params);

    /**
     * 删除流程实例
     * @param instanceId
     * @return void
     * @author 黎勇炫
     * @create 2022/11/8
     * @email 1677685900@qq.com
     */
    void deleteProcessInstance(String instanceId);
}
