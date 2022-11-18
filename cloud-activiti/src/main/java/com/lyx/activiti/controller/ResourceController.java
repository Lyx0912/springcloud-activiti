package com.lyx.activiti.controller;

import com.lyx.activiti.entity.req.ProcessListPageReq;
import com.lyx.activiti.service.IProcessDefinitionService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.exception.BizException;
import com.lyx.common.base.result.R;
import com.lyx.common.base.result.ResultCode;
import com.lyx.common.web.feign.FeignConfig;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 资源controller
 * @author 黎勇炫
 * @date 2022年10月26日 22:17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class ResourceController {

    private final IProcessDefinitionService processDefinitionService;
    private final RepositoryService repositoryService;

     /**
       * 部署一个流程
       */
    @PostMapping("/deployment")
    public R deployment(@RequestParam String name, @RequestParam("file") MultipartFile file) {
        processDefinitionService.deploymentProcess(name,file);
        return R.ok();
    }


     /**
       * 分页查询部署和定义信息
       */
    @GetMapping("/list")
    public R processList(ProcessListPageReq req){
        PageUtils page = processDefinitionService.processList(req);
        return R.ok(page);
    }

     /**
       * 挂起/激活流程
       */
    @PatchMapping("/changeStatus/{defId}")
    public R suspendProcess(@PathVariable String defId){
        processDefinitionService.changeStatus(defId);
        return R.ok();
    }

     /**
       * 根据id删除流程
       */
    @DeleteMapping("/{id}")
    public R deleteProcessDef(@PathVariable String id){
        processDefinitionService.removeDefById(id);
        return R.ok();
    }

    /**
     * 下载流程xml文件
     * @param id
     * @param response
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/4
     * @email 1677685900@qq.com
     */
    @GetMapping("/downloadXml/{id}")
    public R downloadResource(@PathVariable String id, HttpServletResponse response){
        try {
            InputStream fis = processDefinitionService.downXml(id);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Length", "" + buffer.length);
            response.setContentType("text/xml");

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            throw new BizException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
        //return response;
        return R.failed();
    }
}
