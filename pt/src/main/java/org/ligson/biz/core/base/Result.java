package org.ligson.biz.core.base;

import java.io.Serializable;

import org.ligson.biz.core.user.enums.FailureCodeEnum;
import org.ligson.biz.core.user.enums.ResultCodeEnum;


/**
 * 通用返回实体
 */
public class Result<T> implements Serializable {


    private static final long serialVersionUID = 1L;

    private String resultCode;
    private String failureCode;
    private String failureMessage;
    private T data;

    public Result() {
    }

    /**
     * 获取成功返回结果
     *
     * @param data
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> Result<T> getSuccessResult(T data) {
        Result result = new Result();
        result.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    /**
     * 不用new result的方法
     *
     * @param data
     * @param result
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> Result<T> getSuccessResult(T data, Result result) {

        result.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    /**
     * 获取成功返回结果
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Result getSuccessResult() {
        Result result = new Result();
        result.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        return result;
    }

    /**
     * 获取失败返回结果
     *
     * @param failureCode
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> Result<T> getFailureResult(String failureCode) {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(failureCode);
        return result;
    }

    /**
     * 获取失败返回结果
     *
     * @param failureCode f
     * @return result
     */
    @SuppressWarnings("rawtypes")
    public static <T> Result<T> getFailureResult(String failureCode, String failureMessage) {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(failureCode);
        result.setFailureMessage(failureMessage);
        return result;
    }

    /**
     * 获取失败返回结果
     *
     * @param failureCodeEnum fcode
     * @return result
     */
    public static <T> Result<T> getFailureResult(FailureCodeEnum failureCodeEnum) {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(failureCodeEnum.getCode());
        result.setFailureMessage(failureCodeEnum.getMsg());
        return result;
    }

    /**
     * 返回接口处理结果
     */
    public T getData() {
        return data;
    }

    /**
     * 返回失败编码
     */
    public String getFailureCode() {
        return failureCode;
    }

    /**
     * 返回失败原因
     */
    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [resultCode=" + resultCode + ", failureCode="
                + failureCode + ", failureMessage=" + failureMessage
                + ", data=" + data + "]";
    }

}
