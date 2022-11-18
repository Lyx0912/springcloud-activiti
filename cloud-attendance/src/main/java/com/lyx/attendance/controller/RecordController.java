package com.lyx.attendance.controller;


import com.lyx.attendance.entity.Overwork;
import com.lyx.attendance.entity.Record;
import com.lyx.attendance.entity.req.RecordSelectPageReq;
import com.lyx.attendance.service.IRecordService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {

    private final IRecordService recordService;

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
        return R.ok(recordService.getById(id));
    }

    /**
     * 分页查询出勤记录
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @GetMapping
    public R list(RecordSelectPageReq req){
        PageUtils<Record> list = recordService.listPage(req);
        return R.ok(list);
    }

    /**
     * 添加出勤信息
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @PostMapping
    public R save(@RequestBody Record record){
        recordService.save(record);
        return R.ok();
    }

    /**
     * 删除出勤信息
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        recordService.removeById(id);
        return R.ok();
    }

    /**
     * 更新出勤信息
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @PutMapping
    public R update(@RequestBody Record record){
        recordService.updateById(record);
        return R.ok();
    }

}
