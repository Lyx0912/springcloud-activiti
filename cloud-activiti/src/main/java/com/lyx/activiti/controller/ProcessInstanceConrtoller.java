package com.lyx.activiti.controller;

import com.lyx.activiti.service.IProcessDefinitionService;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黎勇炫
 * @date 2022年11月10日 22:53
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/instance")
public class ProcessInstanceConrtoller {

    private final IProcessDefinitionService processDefinitionService;

    /**
     * 删除流程实例
     * @param instanceId 流程实例编号
     * @return com.lyx.common.base.result.R<java.util.List<com.lyx.activiti.entity.vo.ProcessInstanceVO>>
     * @author 黎勇炫
     * @create 2022/11/8
     * @email 1677685900@qq.com
     */
    @DeleteMapping("/{instanceId}")
    public R remove(@PathVariable String instanceId) {
        processDefinitionService.deleteProcessInstance(instanceId);
        return R.ok();
    }
}
