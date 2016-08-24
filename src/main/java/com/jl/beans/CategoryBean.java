package com.jl.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by fannairu on 2016/6/27.
 */
@XmlRootElement
public class CategoryBean extends BaseBean {

    private String name;
    private String memo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
