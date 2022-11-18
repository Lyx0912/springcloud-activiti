package com.lyx.activiti.clients;

import com.lyx.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黎勇炫
 * @date 2022年11月11日 11:59
 */
@FeignClient(value = "cloud-attendance",contextId = "leave")
@RequestMapping("/leave")
public interface LeaveFeignService {

    @PutMapping("/{id}/{result}")
    public R changeResult(@PathVariable Long id, @PathVariable Integer result);
}
