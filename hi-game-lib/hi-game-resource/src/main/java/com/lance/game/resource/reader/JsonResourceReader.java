package com.lance.game.resource.reader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * json资源读取
 *
 * @author Lance
 * @since 2020/12/3
 */
public class JsonResourceReader extends AbstractResourceReader {

    @Override
    public String getType() {
        return "json";
    }

    @Override
    public <T> Iterator<T> read(String path, Class<T> clazz) {
        InputStream inputStream = openInputStream(path);
        // todo 将数据解析成列表，然后返回迭代器
        return null;
    }
}
