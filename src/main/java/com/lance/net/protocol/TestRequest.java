package com.lance.net.protocol;

import com.lance.net.annotation.Protocol;
import lombok.Data;

/**
 * @author Lance
 * @since 2019/10/23 22:07
 */
@Protocol(1000)
@Data
public class TestRequest {

    private String msg;

}
