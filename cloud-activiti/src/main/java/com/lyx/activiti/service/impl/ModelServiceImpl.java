package com.lyx.activiti.service.impl;

import com.lyx.activiti.entity.req.ModelListPageReq;
import com.lyx.activiti.entity.req.ModelSaveReq;
import com.lyx.activiti.entity.req.ModelUpdateReq;
import com.lyx.activiti.entity.vo.ModelVO;
import com.lyx.activiti.entity.vo.ProcessInfoVO;
import com.lyx.activiti.service.IModelService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.exception.BizException;
import com.lyx.common.base.result.R;
import com.lyx.common.base.result.ResultCode;
import lombok.RequiredArgsConstructor;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 18:45
 */
@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements IModelService {

    private final RepositoryService repositoryService;

    /**
     * 分页查询资源模型信息
     *
     * @param req
     */
    @Override
    public PageUtils<ModelVO> list(ModelListPageReq req) {
        PageUtils<ModelVO> page = new PageUtils<>();
        // 获取资源模型查询对象
        ModelQuery modelQuery = repositoryService.createModelQuery();
        if(StringUtils.isNotEmpty(req.getKeyword())) {
            modelQuery.modelNameLike("%"+req.getKeyword()+"%");
        }
        List<Model> models = modelQuery.listPage((int) ((req.getPageNo() - 1) * req.getPageSize()), req.getPageSize().intValue());
        long count = modelQuery.count();
        page.setTotal(count);
        if(count>0){
            List<ModelVO> vos = models.stream().map(model -> {
                ModelVO vo = new ModelVO();
                BeanUtils.copyProperties(model, vo);
                return vo;
            }).collect(Collectors.toList());
            page.setList(vos);
        }
        return page;
    }

    /**
     * 删除模型
     * @param id
     * @return void
     * @author 黎勇炫
     * @create 2022/11/4
     * @email 1677685900@qq.com
     */
    @Override
    public void deleteModel(String id) {
        Model model = repositoryService.getModel(id);
        if(StringUtils.isNotEmpty(model.getDeploymentId())){
            repositoryService.deleteDeployment(model.getDeploymentId());
        }
        repositoryService.deleteModel(id);
    }

    /**
     * 新增资源模型
     *
     * @param req
     */
    @Override
    public void addModel(ModelSaveReq req) {
        try {
            // 创建模型
            Model model = repositoryService.newModel();
            // 判断model的标识是否重复
            ModelQuery modelQuery = repositoryService.createModelQuery();
            List<Model> list = modelQuery.modelKey(req.getKey()).list();
            if (list.size() > 0) {
                throw new BizException(ResultCode.KEY_IS_DUPLICATED);
            }
            model.setMetaInfo("");
            BeanUtils.copyProperties(req,model);
            //保存模型
            repositoryService.saveModel(model);
            // 保存模型到bytearray
            repositoryService.addModelEditorSource(model.getId(),req.getXml().getBytes("UTF-8"));

        } catch (Exception e) {
            throw new BizException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 获取资源xml
     *
     * @param id
     * @return java.io.InputStream
     * @author 黎勇炫
     * @create 2022/11/5
     * @email 1677685900@qq.com
     */
    @Override
    public byte[] loadXml(String id) {
        byte[] modelEditorSource = repositoryService.getModelEditorSource(id);
        return modelEditorSource;
    }

    /**
     * 更新模型的资源模型
     *
     * @param req
     * @return void
     * @author 黎勇炫
     * @create 2022/11/6
     * @email 1677685900@qq.com
     */
    @Override
    public void updateModel(ModelUpdateReq req) {
        // 获取模型
        Model model = repositoryService.getModel(req.getId());
        repositoryService.addModelEditorSource(req.getId(),req.getXml().getBytes(StandardCharsets.UTF_8));
    }
}
