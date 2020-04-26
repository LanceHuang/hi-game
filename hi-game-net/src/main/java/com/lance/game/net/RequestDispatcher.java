package com.lance.game.net;

import java.util.Map;

/**
 * 请求分发
 *
 * @author Lance
 * @since 2019/11/22 15:28
 */
public class RequestDispatcher {



    // todo 需要有个地方记录register所有的 请求-处理器
//    协议号 -> 处理器？
//    类 -> 处理器？
//    private Map<Integer, Handler> handlerMap;

    public void dispatch(Session session, int reqNo, Object req) {
//        handlerMap.get(reqNo)
        // todo
    }

}
