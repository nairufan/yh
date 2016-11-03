package com.jl.model.assembles;

import com.jl.entity.AdviceEntity;
import com.jl.model.AdviceModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fannairu on 2016/8/2.
 */
@Component
public class AdviceAssemble {
    public AdviceModel assembleAdviceModel(AdviceEntity adviceEntity) {
        AdviceModel adviceModel = new AdviceModel();
        adviceModel.setStatus(adviceEntity.getStatus());
        adviceModel.setCreateTime(adviceEntity.getCreateTime());
        adviceModel.setContent(adviceEntity.getContent());
        adviceModel.setId(adviceEntity.getId());
        adviceModel.setUserId(adviceEntity.getUserId());
        adviceModel.setUserIdStr(new Long(adviceEntity.getUserId()).toString());
        return adviceModel;
    }

    public List<AdviceModel> assembleAdviceModelList(Iterable<AdviceEntity> adviceEntities) {
        List<AdviceModel> adviceModels = new ArrayList<AdviceModel>();
        for (AdviceEntity entity : adviceEntities) {
            adviceModels.add(assembleAdviceModel(entity));
        }

        return adviceModels;
    }
}
