package com.lyx.activiti.controller;

import com.lyx.activiti.constant.ActivitiConstant;
import com.lyx.activiti.entity.vo.ProcessInstanceVO;
import com.lyx.activiti.service.IApplyLeaveService;
import com.lyx.activiti.service.IAttTaskService;
import com.lyx.activiti.service.IProcessDefinitionService;
import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年11月07日 21:52
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/leave")
public class ApplyLeaveController {

    private final IApplyLeaveService leaveService;
    private final IAttTaskService taskService;
    private final IProcessDefinitionService processDefinitionService;

    /**
     * 启动流程
     */
    @PostMapping("/startProcess")
    public R startProcess(@RequestParam Map<String,String> params) {
        String instanceId = leaveService.startLeaveProcessInstance(params);
        return R.ok(instanceId);
    }

    /**
     * 获取请假的节点信息
     */
    @GetMapping("/leaveInfo/{ids}")
    public R<List<NodeDTO>> leaveInfo(@PathVariable List<Long> ids) {
        List<NodeDTO> dtos = taskService.buildNodeInfo(ActivitiConstant.LEAVE_DEFINITION_KEY,ids);
        return R.ok(dtos);
    }

}
