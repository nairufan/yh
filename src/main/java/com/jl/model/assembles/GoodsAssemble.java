package com.jl.model.assembles;

import com.jl.entity.GoodsEntity;
import com.jl.model.GoodsModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by fannairu on 2016/6/29.
 */
@Component
public class GoodsAssemble {
    public GoodsModel assembleGoodsModel(GoodsEntity entity) {
        GoodsModel model = new GoodsModel();
        model.setId(entity.getId());
        model.setPics(entity.getPics());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
        model.setMemo(entity.getMemo());
        model.setCategoryId(entity.getCategoryId());
        model.setInventory(entity.getInventory());
        model.setProductName(entity.getProductName());
        return model;
    }

    public List<GoodsModel> assembleGoodsModelList(Iterable<GoodsEntity> goodsEntityIterable) {
        if (goodsEntityIterable == null) {
            return null;
        }
        List<GoodsModel> goodsModelList = new ArrayList<GoodsModel>();
        for (GoodsEntity entity : goodsEntityIterable) {
            goodsModelList.add(assembleGoodsModel(entity));
        }
        return goodsModelList;
    }

    public Map<Long, GoodsModel> assembleGoodsModelMap(Iterable<GoodsEntity> goodsEntityIterable) {
        if (goodsEntityIterable == null) {
            return null;
        }
        Map<Long, GoodsModel> goodsModelMap = new HashMap<Long, GoodsModel>();
        for (GoodsEntity entity : goodsEntityIterable) {
            goodsModelMap.put(entity.getId(), assembleGoodsModel(entity));
        }
        return goodsModelMap;
    }
}
