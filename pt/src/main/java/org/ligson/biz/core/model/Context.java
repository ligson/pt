package org.ligson.biz.core.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.ligson.biz.core.base.DataStore;
import org.ligson.biz.core.base.Result;
import org.ligson.biz.core.exception.UserError;


/**
 * 上下文对象基类
 */
@SuppressWarnings("rawtypes")
public class Context {

    /**
     * 业务时间
     */
    private Integer businessDate;
    /**
     * the data store which holds all data
     */
    public DataStore dataStore = new DataStore();
    /**
     * 返回结果 对象
     */
    private Result result;

    /**
     * Error Code
     */
    public UserError currentError;

    /**
     * 系统前缀
     */
    private String sysPrefix;
    /**
     * 应用代码
     */
    private String appCode;
    /**
     * 当前业务时间
     */
    private Date currentDate;

    private Map<String, Object> attrMap = new HashMap<String, Object>();

    public void setAttr(String attr, Object value) {
        attrMap.put(attr, value);
    }

    public Object getAttr(String attr) {
        return attrMap.get(attr);
    }


    public Set<String> getAttributeNames() {
        return attrMap.keySet();
    }

    public void removeAttr(String attr) {
        attrMap.remove(attr);
    }


    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSysPrefix() {
        return sysPrefix;
    }

    public void setSysPrefix(String sysPrefix) {
        this.sysPrefix = sysPrefix;
    }

    public Integer getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Integer businessDate) {
        this.businessDate = businessDate;
    }

    public DataStore getDataStore() {
        return dataStore;
    }

    public void setDataStore(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public UserError getCurrentError() {
        return currentError;
    }

    public void setCurrentError(UserError currentError) {
        this.currentError = currentError;
    }

    public Map<String, Object> getAttrMap() {
        return attrMap;
    }

    public void setAttrMap(Map<String, Object> attrMap) {
        this.attrMap = attrMap;
    }

    @Override
    public String toString() {
        return "Context{" +
                "businessDate=" + businessDate +
                ", dataStore=" + dataStore +
                ", result=" + result +
                ", currentError=" + currentError +
                ", sysPrefix='" + sysPrefix + '\'' +
                ", appCode='" + appCode + '\'' +
                ", currentDate=" + currentDate +
                ", attrMap=" + attrMap +
                '}';
    }
}
