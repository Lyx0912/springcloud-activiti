package com.lyx.attendance.service;

import com.lyx.attendance.entity.Travel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.attendance.entity.req.TravelListPageReq;
import com.lyx.attendance.entity.req.TravelSaveReq;
import com.lyx.attendance.entity.vo.TravelVO;
import com.lyx.common.base.entity.PageUtils;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
public interface ITravelService extends IService<Travel> {

    /**
     * 分页查询出差信息表
     * @return com.lyx.common.base.entity.PageUtils<com.lyx.attendance.entity.vo.TravelVO>
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    PageUtils<TravelVO> listPage(TravelListPageReq req);


    /**
     * 新增出差信息
     * @param req
     * @return void
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    void saveTravel(TravelSaveReq req);

    /**
     * 删除出差信息同时删除流程实例
     * @param id
     * @param instantId
     * @return void
     * @author 黎勇炫
     * @create 2022/11/10
     * @email 1677685900@qq.com
     */
    void removeTravelAndProcessByInstanceId(Long id, String instantId);
}
