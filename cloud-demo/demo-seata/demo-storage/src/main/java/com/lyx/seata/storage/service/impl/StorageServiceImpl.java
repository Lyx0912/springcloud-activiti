package com.lyx.seata.storage.service.impl;

import com.lyx.seata.storage.entity.Storage;
import com.lyx.seata.storage.mapper.StorageMapper;
import com.lyx.seata.storage.service.IStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {

    @Override
    public void decrease(Long productId, Integer count) {
        // 扣减库存
        Storage storage = this.getById(productId);
        if(!Objects.isNull(storage)){
            storage.setUsed(storage.getUsed()+count);
            storage.setResidue(storage.getResidue()-count);
            this.updateById(storage);
        }
    }
}
