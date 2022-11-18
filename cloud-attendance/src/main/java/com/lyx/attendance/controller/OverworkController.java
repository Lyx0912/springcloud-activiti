package com.lyx.attendance.controller;


import com.lyx.attendance.entity.Overwork;
import com.lyx.attendance.entity.req.OverworkSelectReq;
import com.lyx.attendance.service.IOverworkService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/overwork")
@RequiredArgsConstructor
public class OverworkController {

    private final IOverworkService overworkService;

    /**
     * 批量新增
     * @param file
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/12
     * @email 1677685900@qq.com
     */
    @PostMapping("/saveBatch")
    public R saveBatch(MultipartFile file){
        overworkService.saveBatchByExcel(file);
        return R.ok();
    }

    /**
     * 加班信息详情
     * @param id
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/12
     * @email 1677685900@qq.com
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable Long id){
        return R.ok(overworkService.getById(id));
    }

    /**
     * 分页查询加班记录
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @GetMapping
    public R list(OverworkSelectReq req){
        PageUtils<Overwork> list = overworkService.listPage(req);
        return R.ok(list);
    }

    /**
     * 添加加班信息
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @PostMapping
    public R save(@RequestBody Overwork overwork){
        // 计算加班时长
        Duration duration = Duration.between(overwork.getStartTime(),overwork.getEndTime());
        overwork.setDuration(String.valueOf(duration.toHours()));
        overworkService.save(overwork);
        return R.ok();
    }

    /**
     * 删除加班信息
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        overworkService.removeById(id);
        return R.ok();
    }

    /**
     * 更新加班信息
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @PutMapping
    public R update(@RequestBody Overwork overwork){
        // 重新计算加班时长
        Duration duration = Duration.between(overwork.getStartTime(),overwork.getEndTime());
        overwork.setDuration(String.valueOf(duration.toHours()));
        overworkService.updateById(overwork);
        return R.ok();
    }

}
