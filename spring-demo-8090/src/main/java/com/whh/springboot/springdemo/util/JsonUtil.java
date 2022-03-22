package com.whh.springboot.springdemo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.whh.springboot.springdemo.domain.Greeting;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;

@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 从JSON到java object  
        // 没有匹配的属性名称时不作失败处理  
        objectMapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);

        // 反序列化  
        // 禁止遇到空原始类型时抛出异常，用默认值代替。  
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // 禁止遇到未知（新）属性时报错，支持兼容扩展  
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        // 按时间戳格式读取日期  
        // objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);  
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        // 序列化  
        // 禁止序列化空值  
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        // 按时间戳格式生成日期
        // objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);  
        objectMapper.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
        objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        // 不包含空值属性  
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // objectMapper.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true);  
        // 是否缩放排列输出，默认false，有些场合为了便于排版阅读则需要对输出做缩放排列  
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);

        //开启序列化和反序列化时为对象附加@class属性（坑）
//        objectMapper.activateDefaultTyping(
//                LaissezFaireSubTypeValidator.instance ,
//                ObjectMapper.DefaultTyping.NON_FINAL,
//                JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public static <T> T jsonToObject(String json, Class<T> valueType) {
        T t = null;
        try {
            t = objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            log.error("jsonToObject",e);
            return t;
        }
        return t;
    }

    public static String objectToString(Object o) {
       String value= null;
        try {
            value = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("objectToString",e);
            return value;
        }
        return value;
    }

    public static void main(String[] args) {
        Greeting greeting = new Greeting(1,"hello");
        String s = JsonUtil.objectToString(greeting);
        System.out.println(s);
        greeting = JsonUtil.jsonToObject(s, Greeting.class);
        System.out.println(greeting);
    }
}
