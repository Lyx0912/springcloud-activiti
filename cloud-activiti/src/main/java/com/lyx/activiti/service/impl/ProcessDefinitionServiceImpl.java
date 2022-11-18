package com.lyx.activiti.service.impl;

import com.lyx.activiti.entity.req.ProcessListPageReq;
import com.lyx.activiti.entity.vo.ProcessInfoVO;
import com.lyx.activiti.service.IProcessDefinitionService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.exception.BizException;
import com.lyx.common.base.result.ResultCode;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 15:55
 */
@Service
@RequiredArgsConstructor
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService {

    private final RepositoryService repositoryService;
    private final RuntimeService runtimeService;

    /**
     * 部署流程
     */
    @Override
    public void deploymentProcess(String processName, MultipartFile file) {
        // 文件名称
        InputStream fileInputStream = null;
        // 文件名
        String fileName = file.getOriginalFilename();
        try {
            // 得到输入流（字节流）对象
            fileInputStream = file.getInputStream();
            // 文件的扩展名
            repositoryService.createDeployment()//初始化流程
                    .addInputStream(fileName, fileInputStream)
                    .name(processName)
                    .deploy();
        } catch (Exception e) {
            throw new BizException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 分页查询部署和定义信息
     *
     * @param req
     */
    @Override
    public PageUtils processList(ProcessListPageReq req) {
        PageUtils<ProcessInfoVO> page = new PageUtils<>();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionId().orderByProcessDefinitionVersion().desc();
        // 关键词查询(NAME)
        if(StringUtils.isNoneEmpty(req.getKeyword())) {
            processDefinitionQuery.processDefinitionNameLike("%"+req.getKeyword()+"%");
        }
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.listPage((int) ((req.getPageNo() - 1) * req.getPageSize()), req.getPageSize().intValue());
        long count = processDefinitionQuery.count();
        // 总数
        page.setTotal(count);
        if (count!=0) {
            List<ProcessInfoVO> vos = processDefinitions.stream().map(pdl -> {
                ProcessInfoVO vo = new ProcessInfoVO();
                BeanUtils.copyProperties(pdl, vo);
                vo.setStatus(pdl.isSuspended()==true?2:1);
                System.out.println(vo.getStatus());
                return vo;
            }).collect(Collectors.toList());

            page.setList(vos);
        }
        return page;
    }

    /**
     * 挂起/激活流程
     *
     * @param defId
     */
    @Override
    public void changeStatus(String defId) {
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionId(defId).singleResult();
        boolean suspended = definition.isSuspended();
        // 判断是否挂起
        if(suspended){
            // 挂起的-》激活
            repositoryService.activateProcessDefinitionById(definition.getId());
        }else {
            repositoryService.suspendProcessDefinitionById(definition.getId());
        }
    }

    /**
     * 根据部署id删除流程(级联删除)
     *
     * @param id
     * @return void
     * @author 黎勇炫
     * @create 2022/11/4
     * @email 1677685900@qq.com
     */
    @Override
    public void removeDefById(String id) {
        repositoryService.deleteDeployment(id,true);
    }

    /**
     * 下载流程xml文件
     * @param id 部署编号
     * @return java.io.InputStream
     * @author 黎勇炫
     * @create 2022/11/4
     * @email 1677685900@qq.com
     */
    @Override
    public InputStream downXml(String id) {
        // 获取对应的定义
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
        return repositoryService.getResourceAsStream(definition.getDeploymentId(),definition.getResourceName());
    }

    /**
     * 启动流程
     *
     * @param defId 流程定义id
     * @param busId 业务id
     */
    @Override
    public String startProcessInstance(String defId, Long busId, Map<String,Object> params) {
        // 启动流程实例并且关联业务key
        ProcessInstance instance = runtimeService.startProcessInstanceById(defId, busId.toString(), params);
        return instance.getProcessDefinitionId();
    }

    /**
     * 删除流程实例
     *
     * @param instanceId
     * @return void
     * @author 黎勇炫
     * @create 2022/11/8
     * @email 1677685900@qq.com
     */
    @Override
    public void deleteProcessInstance(String instanceId) {
        ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        if(!Objects.isNull(instance)){
            runtimeService.deleteProcessInstance(instanceId,"");
        }
    }
}
