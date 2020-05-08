演示项目

### 构思
1. 配置数据放在MongoDB，业务数据也暂时先放MongoDB，通过manager层CRUD
2. Controller-Service-Manager-Model 视图到时考虑用U3D
3. 网络层准备用Netty管理，数据传输用protobuf格式

### 正在思考的问题
1. 如果要保证同步，我就要创建游戏房间，将玩家扔到游戏房间里。要创建这样的游戏房间，就要创建线程池，按房间号取模，游戏房间内玩家的所有操作都要在同一条线程上。创建线程池后，如果不同业务都用到，我还要创建大量的线程池。
2. XXCommand中想调用到XXService，按Spring的思想是要将XXService注入到XXCommand，但XXCommand是会不断创建的

### 相应的想法
1. 能不能创建通用的线程池和业务线程池？玩家操作都可以放到通用线程池，玩法、特定游戏逻辑都可以放到业务线程池，这样即便战斗卡住，也不会阻塞其他玩家操作。就是不知道LOL他们是怎么做的，听说是分大厅服和房间服，房间服可以横向拓展
2. 通过ApplicationContext查询XXService，请求频繁时会导致性能降低。还有种办法就是将XXService注入到GameContext，然后XXCommand可直接调用，但会导致GameContext的成员变量暴增