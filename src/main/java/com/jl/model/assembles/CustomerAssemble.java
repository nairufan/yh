package com.jl.model.assembles;

import com.jl.entity.CustomerEntity;
import com.jl.model.CustomerModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fannairu on 2016/6/28.
 */
@Component
public class CustomerAssemble {
    public CustomerModel assembleCustomerModel(CustomerEntity entity) {
        CustomerModel model = new CustomerModel();
        model.setId(entity.getId());
        model.setUpdateTime(entity.getUpdateTime());
        model.setCreateTime(entity.getCreateTime());
        model.setCustomerName(entity.getCustomerName());
        model.setCustomerTel(entity.getCustomerTel());
        model.setMemo(entity.getMemo());
        model.setCustomerAddress(entity.getCustomerAddress());
        model.setLevel(entity.getLevel());
        model.setPics(entity.getPics());
        model.setIsDefault(entity.getIsDefault());
        model.setCustomerGroup(entity.getCustomerGroup());
        model.setExistStatus(entity.getExistStatus());
        return model;
    }

    public List<CustomerModel> assembleCustomerModelList(Iterable<CustomerEntity> customerEntityIterable) {
        if (customerEntityIterable == null) {
            return null;
        }
        List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
        for (CustomerEntity customerEntity : customerEntityIterable) {
            customerModelList.add(assembleCustomerModel(customerEntity));
        }
        return customerModelList;
    }
}
