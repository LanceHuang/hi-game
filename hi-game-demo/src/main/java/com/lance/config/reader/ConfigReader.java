package com.lance.config.reader;

/**
 * 配置解析器
 *
 * @author Lance
 */
public interface ConfigReader {
    // todo

    /**
     * 解析器类型
     */
    String getType();

    /**
     * 解析文件后缀
     */
    String getSuffix();

}
