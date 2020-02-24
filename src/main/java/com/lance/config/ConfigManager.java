package com.lance.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 配置管理器
 *
 * @author Lance
 */
public class ConfigManager implements ApplicationContextAware {

    /** 配置路径 */
    private String location;
    /** 解析器类型 */
    private String type;
    /** 类扫描路径 */
    private String scanPackage;

    private ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }


}
