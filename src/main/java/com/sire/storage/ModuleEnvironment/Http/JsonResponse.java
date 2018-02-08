package com.sire.storage.ModuleEnvironment.Http;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/8/29
 * Author:Sire
 * Description:
 * ==================================================
 */
public class JsonResponse<T> implements Serializable {
    @Ignore
    private static final long serialVersionUID = 5713057484302853885L;
    private int code;
    private T data;
    private String message;



    public JsonResponse(int code, T data,String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public JsonResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public JsonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static JsonResponse createOkResoponse() {
        return createMessageResponse(HttpBusinessCode.OK, "success");
    }

    public static JsonResponse createValidateErrorResposne(BindingResult result) {
        List<ObjectError> errorList = result.getAllErrors();
        List<String> stringList = errorList.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        JsonResponse<List<String>> jsonResponse = new JsonResponse<>(HttpBusinessCode.DATA_VERIFY_ERROR, stringList);
        return jsonResponse;
    }

    public static JsonResponse createMessageResponse(int code, String message) {
        return createSimpleDataResponse(code, null,message);
    }

    public static JsonResponse createSimpleDataResponse(int code, Map<String, Object> messageInfor,String message) {
        JsonResponse<Map<String, Object>> jsonResponse = new JsonResponse<>(code, messageInfor,message);
        return jsonResponse;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
