package com.lyx.admin.ser.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lyx.admin.dto.ClientDTO;
import com.lyx.admin.ser.entity.SysOauthClient;
import com.lyx.admin.ser.service.ISysOauthClientService;
import com.lyx.common.base.result.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黎勇炫
 * @date 2022年10月10日 17:52
 */
@RequestMapping("/api/oauth-clients")
@Slf4j
@AllArgsConstructor
@RestController
public class OauthClientController {

    private ISysOauthClientService iSysOauthClientService;

    @GetMapping("/getClientById")
    public R<ClientDTO> getOAuth2ClientById(@RequestParam String clientId) {
        SysOauthClient client = iSysOauthClientService.getById(clientId);
        Assert.notNull(client, "OAuth2 客户端不存在");
        ClientDTO oAuth2ClientDTO = new ClientDTO();
        BeanUtil.copyProperties(client, oAuth2ClientDTO);
        return R.ok(oAuth2ClientDTO);
    }
}