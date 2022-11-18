package com.lyx.activiti.service;

import com.lyx.activiti.entity.req.ModelListPageReq;
import com.lyx.activiti.entity.req.ModelSaveReq;
import com.lyx.activiti.entity.req.ModelUpdateReq;
import com.lyx.activiti.entity.vo.ModelVO;
import com.lyx.common.base.entity.PageUtils;

import java.io.InputStream;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 18:41
 */
public interface IModelService {
     /**
       * 分页查询资源模型信息
       */
     PageUtils<ModelVO> list(ModelListPageReq req);

      /**
        * 删除模型
        */
     void deleteModel(String id);

      /**
        * 新增资源模型
        */
    void addModel(ModelSaveReq req);

    /**
     * 获取资源xml
     * @param id
     * @return java.io.InputStream
     * @author 黎勇炫
     * @create 2022/11/5
     * @email 1677685900@qq.com
     */
    byte[] loadXml(String id);

    /**
     * 更新模型的资源模型
     * @param id
     * @param xml
     * @return void
     * @author 黎勇炫
     * @create 2022/11/6
     * @email 1677685900@qq.com
     */
    void updateModel(ModelUpdateReq req);
}
