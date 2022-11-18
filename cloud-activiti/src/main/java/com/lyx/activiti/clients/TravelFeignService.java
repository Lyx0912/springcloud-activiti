package com.lyx.activiti.clients;

import com.lyx.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黎勇炫
 * @date 2022年11月10日 19:58
 */
@FeignClient(value = "cloud-attendance",contextId = "businessTrip")
@RequestMapping("/travel")
public interface TravelFeignService {

    @PutMapping("/{id}/{result}")
    public R changeResult(@PathVariable Long id, @PathVariable Integer result);

}
