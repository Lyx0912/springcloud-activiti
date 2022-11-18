package com.lyx.activiti.controller;

import com.lyx.activiti.entity.req.TaskHandleReq;
import com.lyx.activiti.entity.req.TaskListPageReq;
import com.lyx.activiti.entity.vo.ProcessInstanceVO;
import com.lyx.activiti.entity.vo.TaskVO;
import com.lyx.activiti.service.IAttTaskService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年11月09日 13:47
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final IAttTaskService attTaskService;

    @GetMapping("/taskByRoles")
    public R taskByRoles(TaskListPageReq req){
        PageUtils<TaskVO> vos= attTaskService.getTaskListByRoles(req);
        return R.ok(vos);
    }

    /**
     * 流程进度查询
     * @param instanceId
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/8
     * @email 1677685900@qq.com
     */
    @GetMapping("/taskHistory/{instanceId}")
    public R<List<ProcessInstanceVO>> history(@PathVariable String instanceId) {
        List<ProcessInstanceVO> vos = attTaskService.processInstanceHistory(instanceId);
        return R.ok(vos);
    }

    /**
     * 完成任务
     * @param req
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/8
     * @email 1677685900@qq.com
     */
    @PostMapping("/handle")
    public R<List<ProcessInstanceVO>> handle(@Validated @RequestBody TaskHandleReq req) {
        attTaskService.handleTask(req);
        return R.ok();
    }
}
