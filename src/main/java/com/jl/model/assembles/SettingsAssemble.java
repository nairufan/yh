package com.jl.model.assembles;

import com.jl.entity.SettingsEntity;
import com.jl.model.SettingsModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fannairu on 2016/7/17.
 */
@Component
public class SettingsAssemble {
    public SettingsModel assembleSettingsModel(SettingsEntity entity) {
        SettingsModel model = new SettingsModel();
        model.setStringKey(entity.getStringKey());
        model.setStringValue(entity.getStringValue());
        return model;
    }

    public List<SettingsModel> assembleSettingsModelList(Iterable<SettingsEntity> settingsEntityIterable) {
        List<SettingsModel> settingsModelList = new ArrayList<SettingsModel>();
        for (SettingsEntity settingsEntity: settingsEntityIterable) {
            settingsModelList.add(assembleSettingsModel(settingsEntity));
        }
        return settingsModelList;
    }
}
