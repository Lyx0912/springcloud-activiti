package com.lyx.seata.account.service.impl;

import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.lyx.seata.account.entity.Account;
import com.lyx.seata.account.mapper.AccountMapper;
import com.lyx.seata.account.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-10-13
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Override
    public void decrease(Long userId, Integer money) {
        Account account = this.getById(userId);
        if(!Objects.isNull(account)){
            account.setUsed(account.getUsed().add(new BigDecimal(money)));
            account.setResidue(account.getResidue().subtract(new BigDecimal(money)));
            this.updateById(account);
        }
    }
}
