package com.lyx.admin.api;

import com.lyx.admin.dto.UserAuthDTO;
import com.lyx.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 黎勇炫
 * @date 2022年10月10日 17:10
 */
@FeignClient(value = "cloud-admin")
public interface UserFeignClient {

    @GetMapping("/user/getByUserName/{username}")
    R<UserAuthDTO> getUserByUsername(@PathVariable String username);
}