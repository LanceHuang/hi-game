# 配置资源读取

**初始化**：
1. 扫描配置类
2. 扫描配置目录
3. 生成ResourceStorage
4. 初始化ResourceStorage
5. 注册到ResourceStorageManager
5. 注入标注了@GameStorageInject的bean成员变量

**热更**：
1. 获取ResourceStorage
2. 重新初始化，覆盖原有数据

### 问题
1. 热更：触发热更逻辑时，调用多个业务类的reload
2. 配表：策划不希望使用json
