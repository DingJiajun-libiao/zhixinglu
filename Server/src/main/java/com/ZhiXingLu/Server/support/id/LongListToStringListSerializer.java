package com.ZhiXingLu.Server.support.id;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * @author closer
 * @Description: 将 Long 类型的 ID 字段序列化为 String
 * @Create: 2026/4/4 22:21
 */
public class LongListToStringListSerializer extends JsonSerializer<List<Long>> {

    @Override
    public void serialize(List<Long> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }
        gen.writeStartArray();
        for (Long item : value) {
            gen.writeString(item == null ? null : item.toString());
        }
        gen.writeEndArray();
    }
}
