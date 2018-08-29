package com.wiikzz.library.core.crypt;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by wiikii on 2018/8/29.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
@RunWith(AndroidJUnit4.class)
public class AESCryptToolTest {

    @Test
    public void testAESCryptTool() {
        String source = "0e830400451993494058024219903391";
        String result = AESCryptTool.encrypt(source);
        assertEquals("a8707ce80fecb71dd278e93d25b67bfd02ffae40c3c18ddb5fa3cf52ddb8aa4017552e49b1eaf572df83be98cd84f0d6", result);

        source = "a8707ce80fecb71dd278e93d25b67bfd02ffae40c3c18ddb5fa3cf52ddb8aa4017552e49b1eaf572df83be98cd84f0d6";
        result = AESCryptTool.decrypt(source);
        assertEquals("0e830400451993494058024219903391", result);

        source = "{\"adjustment\":null,\"festival\":null,\"festivalCommon\":null,\"tools\":null,\"config\":null,\"time\":1535442087,\"info\":{\"status\":\"200\",\"message\":\"数据请求成功\",\"time\":\"2018-08-28\"}}";
        result = AESCryptTool.encrypt(source);
        assertEquals("0754a396a8b1b4d1aaa472775f870d033f2c2b0e8aef3b7f31ee9365b693614ba8dd05aa42c02119d79cb5c222fc783928b36047a8829936bb8b04f53d1844ff8e8ecae432970cc4472a992bf63cb5b4117754474dd532f60dfb5c989ef81d693f7a68d3110b68c7ef1a007947e8fac6d67ce61307dc95bc7c2e0cff51d4b1860227458acd887863f112ab19dffdbaac470ad8c110f5df3a75b74fab8fdef505d4c3ab01cbf5d6e054ea25ee847cd1b4c16427e72467096cd932b3ea0a1fe857", result);

        source = "0754a396a8b1b4d1aaa472775f870d033f2c2b0e8aef3b7f31ee9365b693614ba8dd05aa42c02119d79cb5c222fc783928b36047a8829936bb8b04f53d1844ff8e8ecae432970cc4472a992bf63cb5b4117754474dd532f60dfb5c989ef81d693f7a68d3110b68c7ef1a007947e8fac6d67ce61307dc95bc7c2e0cff51d4b1860227458acd887863f112ab19dffdbaac470ad8c110f5df3a75b74fab8fdef505d4c3ab01cbf5d6e054ea25ee847cd1b4c16427e72467096cd932b3ea0a1fe857";
        result = AESCryptTool.decrypt(source);
        assertEquals("{\"adjustment\":null,\"festival\":null,\"festivalCommon\":null,\"tools\":null,\"config\":null,\"time\":1535442087,\"info\":{\"status\":\"200\",\"message\":\"数据请求成功\",\"time\":\"2018-08-28\"}}", result);
    }
}
