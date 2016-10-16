package com.jl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by fannairu on 2016/6/26.
 */
public abstract class BaseBean {
    protected long id;
    @JsonProperty(value = "create_time")
    protected long createTime;
    @JsonProperty(value = "update_time")
    protected long updateTime;
    @JsonProperty(value = "exist_status")
    protected int existStatus;
    @JsonProperty(value = "app_version")
    protected String appVersion;
    @JsonProperty(value = "system_type")
    protected String systemType;
    @JsonProperty(value = "system_version")
    protected String systemVersion;
    protected String version;
    @JsonProperty(value = "device_id")
    protected String deviceId;
    @JsonProperty(value = "device_mode")
    protected String deviceMode;
    @JsonProperty(value = "connection_mode")
    protected String connectionMode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return new Timestamp(createTime);
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return new Timestamp(updateTime);
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceMode() {
        return deviceMode;
    }

    public void setDeviceMode(String deviceMode) {
        this.deviceMode = deviceMode;
    }

    public String getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(String connectionMode) {
        this.connectionMode = connectionMode;
    }

    public int getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(int existStatus) {
        this.existStatus = existStatus;
    }
}
