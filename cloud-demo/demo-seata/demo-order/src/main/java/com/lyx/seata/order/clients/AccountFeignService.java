package com.lyx.seata.order.clients;

import com.lyx.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 黎勇炫
 * @date 2022年10月14日 15:51
 */
@FeignClient(value = "demo-account")
@RequestMapping("/account")
public interface AccountFeignService {

    @RequestMapping("/decrease")
    public R decrease(@RequestParam("userId") Long userId, @RequestParam("money") Integer money);

}
