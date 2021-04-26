package com.lance.game.net.codec;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 编码解码器注册中心
 *
 * @author Lance
 * @since 2021/4/12
 */
public class CodecRegistry {

    private static final Map<Class<?>, Codec> CODEC_MAP = new HashMap<>();

    private static final Codec DEFAULT_CODEC = new ObjectCodec();

    static {
        CODEC_MAP.put(int.class, new IntCodec());
        CODEC_MAP.put(Integer.class, new IntCodec());

        CODEC_MAP.put(Collection.class, new CollectionCodec());
        CODEC_MAP.put(List.class, new CollectionCodec());
        CODEC_MAP.put(Set.class, new CollectionCodec());

        CODEC_MAP.put(Map.class, new MapCodec());
        CODEC_MAP.put(HashMap.class, new MapCodec());
        CODEC_MAP.put(TreeMap.class, new MapCodec());
    }

    public static Codec getCodec(Class<?> clazz) {
        Codec codec = CODEC_MAP.get(clazz);
        if (codec == null) {
            return DEFAULT_CODEC;
        }
        return codec;
    }
}
