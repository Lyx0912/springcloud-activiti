package com.lyx.activiti.listener;

import com.lyx.activiti.clients.TravelFeignService;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 黎勇炫
 * @date 2022年11月10日 19:14
 */
@Component("businessTripListener")
@RequiredArgsConstructor
public class BusinessTripListener implements ExecutionListener {

    private final TravelFeignService travelFeignService;

    @Value("${processDefinitionId.travel}")
    private String processDefinitionId;

    @Override
    public void notify(DelegateExecution execution) {
        // 如果节点名称为“拒绝”并且流程属于出差
        if(execution.getCurrentFlowElement().getName().equals("同意")&&execution.getProcessDefinitionId().equals(processDefinitionId)){
            // 远程调用
            changeTravelResult(execution,1);
        }else {
            // 远程调用
            changeTravelResult(execution,2);
        }
    }

    private void changeTravelResult(DelegateExecution execution,Integer result) {
        travelFeignService.changeResult(Long.valueOf(execution.getProcessInstanceBusinessKey()),result);
    }
}
