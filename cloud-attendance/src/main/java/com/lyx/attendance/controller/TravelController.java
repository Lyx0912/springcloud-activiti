package com.lyx.attendance.controller;


import com.lyx.attendance.entity.Leave;
import com.lyx.attendance.entity.Travel;
import com.lyx.attendance.entity.req.TravelListPageReq;
import com.lyx.attendance.entity.req.TravelSaveReq;
import com.lyx.attendance.entity.vo.TravelVO;
import com.lyx.attendance.service.ITravelService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/travel")
@RequiredArgsConstructor
public class TravelController {

    private final ITravelService travelService;

    /**
     * 出差信息记录表
     * @param req
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    @GetMapping()
    public R listPage(TravelListPageReq req){
        PageUtils<TravelVO> vos = travelService.listPage(req);
        return R.ok(vos);
    }

    /**
     * 新增出差信息
     * @param req
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    @PostMapping
    public R save(@Validated @RequestBody TravelSaveReq req){
        travelService.saveTravel(req);
        return R.ok();
    }

    /**
     * 修改审核结果
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/10
     * @email 1677685900@qq.com
     */
    @PutMapping("/{id}/{result}")
    public R changeResult(@PathVariable Long id,@PathVariable Integer result){
        // 更新状态
        travelService.lambdaUpdate().eq(Travel::getId,id).set(Travel::getResult,result).update();
        return R.ok();
    }

    /**
     * 删除出差信息
     * @param id
     * @param instantId 流程id
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/7
     * @email 1677685900@qq.com
     */
    @DeleteMapping()
    public R delete(Long id,String instantId){
        travelService.removeTravelAndProcessByInstanceId(id,instantId);
        return R.ok();
    }

    /**
     * 获取出差信息
     * @param id
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable String id){
        Travel travel = travelService.getById(id);
        return R.ok(travel);
    }

}
