package com.jl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fannairu on 2016/7/17.
 */
public class SettingsBean {
    @JsonProperty(value = "string_key")
    private String stringKey;
    @JsonProperty(value = "string_value")
    private String stringValue;

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
