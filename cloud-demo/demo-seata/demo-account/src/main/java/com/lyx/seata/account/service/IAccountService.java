package com.lyx.seata.account.service;

import com.lyx.seata.account.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-10-13
 */
public interface IAccountService extends IService<Account> {

    void decrease(Long userId, Integer money);
}
