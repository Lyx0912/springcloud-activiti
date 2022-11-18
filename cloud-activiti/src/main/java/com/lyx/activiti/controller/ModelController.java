package com.lyx.activiti.controller;

import com.lyx.activiti.entity.req.ModelListPageReq;
import com.lyx.activiti.entity.req.ModelSaveReq;
import com.lyx.activiti.entity.req.ModelUpdateReq;
import com.lyx.activiti.entity.vo.ModelVO;
import com.lyx.activiti.service.IModelService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.R;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 模型管理
 *
 * @author 黎勇炫
 * @date 2022年11月04日 18:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/model")
public class ModelController {

    private final IModelService modelService;
    private final RepositoryService repositoryService;

    /**
     * 分页查询流程模型
     */
    @GetMapping("/list")
    public R list(ModelListPageReq req) {
        PageUtils<ModelVO> page = modelService.list(req);
        return R.ok(page);
    }

    /**
     * 删除模型
     */
    @DeleteMapping("/{id}")
    public R deleteModel(@PathVariable String id) {
        modelService.deleteModel(id);
        return R.ok();
    }

    /**
     * 新增资源模型
     *
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/5
     * @email 1677685900@qq.com
     */
    @PostMapping
    public R addModel(@RequestBody ModelSaveReq req) {
        modelService.addModel(req);
        return R.ok();
    }

    /**
     * 加载模型xml资源
     *
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/5
     * @email 1677685900@qq.com
     */
    @GetMapping("/loadXml/{id}")
    public R loadModelXml(@PathVariable String id, HttpServletResponse response) {
        byte[] buffer = modelService.loadXml(id);
        response.reset();
        // 设置response的Header
        String xml = new String(buffer);
        return R.ok(xml);
    }

    /**
     * 更新模型的模型资源
     * @param req 资源请求req
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/6
     * @email 1677685900@qq.com
     */
    @PutMapping("/updateModel/{id}")
    public R updateModel(@RequestBody ModelUpdateReq req){
        modelService.updateModel(req);
        return R.ok();
    }

    @GetMapping("/downModel/{id}")
    public R downModel(@PathVariable String id){
        byte[] source = repositoryService.getModelEditorSource(id);
        return R.ok(new String(source));
    }


    /**
     * 部署模型
     * @param modelId
     * @return com.lyx.common.base.result.R
     * @author 黎勇炫
     * @create 2022/11/6
     * @email 1677685900@qq.com
     */
    @PostMapping("/deployment/{modelId}")
    public R deployment(@PathVariable String modelId){
        DeploymentBuilder deployment = repositoryService.createDeployment();
        // 获取模型
        Model model = repositoryService.getModel(modelId);
        // 获取模型对应资源
        byte[] bytes = repositoryService.getModelEditorSource(modelId);
        // 部署模型
        Deployment deploy = deployment
                .addString(model.getKey() + ".bpmn20.xml", new String(bytes))
                .name(model.getName())
                .deploy();
        model.setDeploymentId(deploy.getId());
        repositoryService.saveModel(model);
        return R.ok();
    }

}
