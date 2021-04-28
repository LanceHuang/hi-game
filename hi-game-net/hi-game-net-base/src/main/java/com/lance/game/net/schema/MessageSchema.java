package com.lance.game.net.schema;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 * @since 2021/4/28
 */
public abstract class MessageSchema {

    private static final Map<Class<?>, MessageSchema> SCHEMA_MAP = new HashMap<>();

    public static MessageSchema getSchema(Class<?> clazz) {
        MessageSchema schema = SCHEMA_MAP.get(clazz);
        if (schema == null) {
            schema = createSchema(clazz);
            MessageSchema preSchema = SCHEMA_MAP.putIfAbsent(clazz, schema);
            if (preSchema != null) {
                schema = preSchema;
            }
        }
        return schema;
    }

    private static MessageSchema createSchema(Class<?> clazz) {
        // todo
        return null;
    }

    public abstract int getSerializedSize(Object obj);

    public byte[] serialize(Object obj) throws IOException {
        byte[] result = new byte[getSerializedSize(obj)];
        CodedOutputStream output = CodedOutputStream.newInstance(result);
        serialize(output, obj);
        return result;
    }

    public abstract void serialize(CodedOutputStream output, Object obj) throws IOException;

    public Object deserialize(byte[] data) throws IOException {
        CodedInputStream input = CodedInputStream.newInstance(data);
        return deserialize(input);
    }

    public abstract Object deserialize(CodedInputStream input) throws IOException;
}
