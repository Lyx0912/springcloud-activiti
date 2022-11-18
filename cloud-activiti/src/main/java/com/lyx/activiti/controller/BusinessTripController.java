package com.lyx.activiti.controller;

import com.lyx.activiti.constant.ActivitiConstant;
import com.lyx.activiti.service.IAttTaskService;
import com.lyx.activiti.service.IBusinessTripService;
import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年11月10日 17:09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/businessTrip")
public class BusinessTripController {

    private final IBusinessTripService businessTripService;
    private final IAttTaskService taskService;

    /**
     * 启动流程
     */
    @PostMapping("/startProcess")
    public R startProcess(@RequestParam Map<String,String> params) {
        String instanceId = businessTripService.startBusinessTripProcessInstance(params);
        return R.ok(instanceId);
    }

    /**
     * 获取出差的节点信息
     */
    @GetMapping("/travelNodeInfo/{ids}")
    public R<List<NodeDTO>> travelNodeInfo(@PathVariable List<Long> ids) {
        List<NodeDTO> dtos = taskService.buildNodeInfo(ActivitiConstant.BUSINESS_TRIP_DEFINITION_KEY,ids);
        return R.ok(dtos);
    }
}
