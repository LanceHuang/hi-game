package com.lance.game.resource.reader;

import java.io.InputStream;

/**
 * @author Lance
 * @since 2020/12/3
 */
public abstract class AbstractResourceReader implements ResourceReader {

    /**
     * 打开资源流
     *
     * @param path 资源路径
     */
    public InputStream openInputStream(String path) {
        // todo
        return null;
    }
}
