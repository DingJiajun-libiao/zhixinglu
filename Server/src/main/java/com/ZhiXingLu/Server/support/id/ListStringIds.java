package com.ZhiXingLu.Server.support.id;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author closer
 * @Description: 将 List<Long>; 类型的 ID 列表字段序列化为 List<String>;，避免前端 JavaScript 精度丢失
 * @Create: 2026/4/4 22:22
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = LongListToStringListSerializer.class)
public @interface ListStringIds {
}
