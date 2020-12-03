# 配置资源读取

**初始化**：
1. 扫描配置类
2. 扫描配置目录
3. 生成GameResourceStorage
4. 初始化GameResourceStorage
5. 注册到GameResourceStorageManager
5. 注入标注了@GameStorageInject的bean成员变量

**热更**：
1. 获取GameResourceStorage
2. 重新初始化，覆盖原有数据