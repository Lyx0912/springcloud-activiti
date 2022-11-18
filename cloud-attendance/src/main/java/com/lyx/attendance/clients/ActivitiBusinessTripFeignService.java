package com.lyx.attendance.clients;

import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年11月10日 17:27
 */
@FeignClient(value = "cloud-activiti",contextId = "businessTrip")
@RequestMapping("/businessTrip")
public interface ActivitiBusinessTripFeignService {

    /**
     * 启动流程
     */
    @PostMapping("/startProcess")
    public R startProcess(@RequestParam Map<String,String> params);

     /**
       * 获取流程节点信息
       */
     @GetMapping("/travelNodeInfo/{ids}")
     public R<List<NodeDTO>> travelNodeInfo(@PathVariable List<Long> ids);

}
