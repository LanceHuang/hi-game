package com.lance.game.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 参考h2，减少自动扩容
 *
 * @author Lance
 * @since 2021/8/31
 */
public class CollectionUtils {

    public static <T> ArrayList<T> arrayList() {
        return new ArrayList<>(4);
    }

    public static <T> ArrayList<T> arrayList(int initCapacity) {
        return new ArrayList<>(initCapacity);
    }

    public static <K, V> HashMap<K, V> hashMap() {
        return new HashMap<>(4);
    }

    public static <K, V> HashMap<K, V> hashMap(int initCapacity) {
        return new HashMap<>(tableSizeOf(initCapacity));
    }

    /**
     * 避免扩容
     *
     * @param cap 期望容量
     * @return 推荐容量
     */
    private static int tableSizeOf(int cap) {
        if (cap <= 0) {
            return 0;
        } else if (cap <= 2) {
            return 4;
        } else {
            return (int) (cap / 0.75f);
        }
    }

    public static <T> HashSet<T> hashSet() {
        return new HashSet<>(4);
    }

    public static <T> HashSet<T> hashSet(int initCapacity) {
        return new HashSet<>(tableSizeOf(initCapacity));
    }
}
