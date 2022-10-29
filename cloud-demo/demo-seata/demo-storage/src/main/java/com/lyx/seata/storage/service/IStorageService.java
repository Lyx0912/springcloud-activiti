package com.lyx.seata.storage.service;

import com.lyx.seata.storage.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-10-13
 */
public interface IStorageService extends IService<Storage> {

    void decrease(Long productId, Integer count);
}
