# 通用游戏项目框架

---

### 项目分层
```
+-------------------------------------------+
| 客户端          U3d                        |
+-------------------------------------------+
| 网络层          Netty       Protostuff     |
+-------------------------------------------+
|                                           |
| 业务层          事件     配置    场景       |
|                                           |
+-------------------------------------------+
| 持久层          MySQL       Caffeine       |
+-------------------------------------------+
```

### 项目结构
```
hi-game
    hi-game-demo 演示项目
    hi-game-net 网络层模块
    hi-game-orm 持久层模块
    hi-game-config 配置模块
    hi-game-common 通用模块
    hi-game-event 事件模块
    hi-game-loadbalancer 负载均衡模块
    hi-game-lab 实验性内容
```

### 日志目录结构
```
logs
    console.log
    error.log
module-logs
    buff.log
    buff.log.202108310
    test.log
    buff.log.202108310
```

* logs：通用日志
* module-logs：模块日志

### 开发规范
* controller：视图层
* service：服务层
* manager：管理层
* dao：持久层
* model：业务实体
* message：网络协议
* config：业务配置
* entity：持久层实体
* constant：常量及类型
