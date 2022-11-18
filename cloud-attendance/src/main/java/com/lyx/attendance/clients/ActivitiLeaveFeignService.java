package com.lyx.attendance.clients;

import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年11月07日 22:04
 */
@FeignClient(value = "cloud-activiti",contextId = "leave")
@RequestMapping("/leave")
public interface ActivitiLeaveFeignService {

    /**
     * 启动流程
     */
    @PostMapping("/startProcess")
    public R startProcess(@RequestParam Map<String,String> params);


    /**
     * 获取请假的节点信息
     */
    @GetMapping("/leaveInfo/{ids}")
    public R<List<NodeDTO>> leaveInfo(@PathVariable List<Long> ids);

}
