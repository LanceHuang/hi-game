package com.lance.game.resource.reader;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 * @since 2020/12/3
 */
public abstract class AbstractResourceReader implements ResourceReader {

    private static final Map<String, ResourceReader> resourceReaderMap = new HashMap<>();

    @PostConstruct
    public void init() {
        resourceReaderMap.put(getType(), this);
    }

    public static ResourceReader getResourceReader(String type) {
        return resourceReaderMap.get(type);
    }

    /**
     * 获取资源读取类型
     */
    public abstract String getType();
}
