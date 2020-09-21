> 事件框架

[toc]

---

### 结构图
```
event1 ---> EventHandler 1-1
            EventHandler 1-2

event2 ---> EventHandler 2-1

Publisher1 -----event1,event2------> | EventMulticaster | -----------> EventHandler 1-1,EventHandler 1-2,EventHandler 2-1
Publisher2 -----event2-------------> | EventMulticaster | -----------> EventHandler 2-1
```
* 事件可以有多个EventHandler（事件处理器）
* Publisher（事件发布者）可以发布事件。可以发布到本服，也可以发布到多个服
* EventMulticaster（事件多播）将事件传给各个事件处理器

### 事件注册流程
1. 扫描所有bean的方法。若标注了@EventListener，则注册到事件容器

### 事件发布/处理流程
1. 创建事件对象
2. 查询事件的所有监听者
3. 反射调用监听方法

### 一些问题
* 怎么发布异步事件？