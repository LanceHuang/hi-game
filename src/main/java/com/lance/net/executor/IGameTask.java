package com.lance.net.executor;

/**
 * 游戏任务
 * 2019年12月13日12:03:30 session相关的代码不需要统计耗时，可以直接execute，如果用IGameTask的话需要再额外套一层。
 * 统计任务耗时时，其实是直接调用run方法，所以IGameTask和Runnable的区别不大
 *
 * @author Lance
 * @since 2019/12/12 18:24
 */
@Deprecated
public interface IGameTask {

    void run();
}
