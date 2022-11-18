package com.lyx.attendance.clients;

import com.lyx.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黎勇炫
 * @date 2022年11月11日 10:34
 */
@FeignClient(value = "cloud-activiti",contextId = "instance")
@RequestMapping("/instance")
public interface ActivitiProcessInstanceFeignService {

    @DeleteMapping("/{instanceId}")
    public R remove(@PathVariable String instanceId);
}
