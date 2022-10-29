package com.lyx.admin.ser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.admin.ser.entity.SysOauthClient;
import com.lyx.admin.ser.mapper.SysOauthClientMapper;
import com.lyx.admin.ser.service.ISysOauthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author： 黎勇炫
 */
@Service
@RequiredArgsConstructor
public class SysOauthClientServiceImpl extends ServiceImpl<SysOauthClientMapper, SysOauthClient> implements ISysOauthClientService {

}
