package com.sire.storage.ModuleEnvironment.Utils;

import com.alibaba.fastjson.JSON;
import com.sire.storage.ModuleEnvironment.Http.ParamIgnore;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/8/29
 * Author:Sire
 * Description:注意Map<String,Object>中的Object只能是基本类型，不能是对象类型
 * ==================================================
 */
public class ObjectMapConversionUtils {

    public static Map<String, Object> object2Map(Object object) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        Map<String, Object> kv = new LinkedHashMap<>();
        Arrays.stream(declaredFields).filter(field -> {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                return !(field.isAnnotationPresent(ParamIgnore.class) || field.get(object) == null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("参数对象错误");
            }
        }).map(field -> {
            Object outValue = null;
            try {
                outValue = field.get(object);
                kv.put(field.getName(), outValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return outValue;
        }).count();
        return kv;
    }

    public static <T> T map2Object(Map<String, Object> mapParmas, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(mapParmas), clazz);
    }
}
