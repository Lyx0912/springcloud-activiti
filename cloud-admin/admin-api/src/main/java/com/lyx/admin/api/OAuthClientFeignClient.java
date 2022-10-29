package com.lyx.admin.api;

import com.lyx.admin.dto.ClientDTO;
import com.lyx.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 黎勇炫
 * @date 2022年10月10日 17:09
 */
@FeignClient(value = "cloud-admin", contextId = "oauth-client")
public interface OAuthClientFeignClient {

     /**
       * 通过客户端id获取客户端信息
       */
    @GetMapping("/api/oauth-clients/getClientById")
    R<ClientDTO> getOAuth2ClientById(@RequestParam String clientId);
}
