package com.lyx.attendance.controller;


import com.lyx.attendance.entity.Leave;
import com.lyx.attendance.entity.Travel;
import com.lyx.attendance.entity.req.LeaveListPageReq;
import com.lyx.attendance.entity.req.LeaveSaveReq;
import com.lyx.attendance.entity.vo.LeaveVO;
import com.lyx.attendance.service.ILeaveService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/leave")
@RequiredArgsConstructor
public class LeaveController {

    private final ILeaveService leaveService;


    /**
     *  查询出差信息列表
     * @param req
     * @return
     * @author 黎勇炫
     * @create 2022/11/4
     * @email 1677685900@qq.com
     */
   @GetMapping("/list")
   public R list(LeaveListPageReq req){
        PageUtils<LeaveVO> vos = leaveService.listPage(req);
        return R.ok(vos);
    }


    /**
     * 删除请假信息
     * @param id
     * @param instantId 流程id
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/7
     * @email 1677685900@qq.com
     */
    @DeleteMapping()
    public R delete(Long id,String instantId){
        leaveService.removeLeaveAndProcessByInstanceId(id,instantId);
        return R.ok();
    }

    /**
     * 新增请假信息
     * @param req
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/7
     * @email 1677685900@qq.com
     */
    @PostMapping
    public R save(@Validated @RequestBody LeaveSaveReq req){
        // 请假申请
        leaveService.appLeave(req);
        return R.ok();
    }

    /**
     * 获取请假信息
     * @param id
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable String id){
        Leave leave = leaveService.getById(id);
        return R.ok(leave);
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
        leaveService.lambdaUpdate().eq(Leave::getId,id).set(Leave::getResult,result).update();
        return R.ok();
    }


}
