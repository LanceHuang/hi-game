package com.lance.game.resource.reader;

import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * xlsx资源读取
 *
 * @author Lance
 * @since 2020/12/3
 */
@Component
public class XlsxResourceReader extends AbstractResourceReader {

    @Override
    public String getType() {
        return "xlsx";
    }

    @Override
    public <T> Iterator<T> read(String path, Class<T> clazz) {
        // todo
        return null;
    }
}
