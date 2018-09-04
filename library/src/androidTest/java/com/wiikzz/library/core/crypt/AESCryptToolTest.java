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
        assertEquals("a205ccd857ff2963c974ee02d923814e1e3e807b3350a30841621fc2c71d25b772067f007cb6e618f4168f4be5a26b6e", result);

        source = "a205ccd857ff2963c974ee02d923814e1e3e807b3350a30841621fc2c71d25b772067f007cb6e618f4168f4be5a26b6e";
        result = AESCryptTool.decrypt(source);
        assertEquals("0e830400451993494058024219903391", result);

        source = "{\"adjustment\":null,\"festival\":null,\"festivalCommon\":null,\"tools\":null,\"config\":null,\"time\":1535442087,\"info\":{\"status\":\"200\",\"message\":\"数据请求成功\",\"time\":\"2018-08-28\"}}";
        result = AESCryptTool.encrypt(source);
        assertEquals("b00d00fdc25fb1a3e354527393e0d2ad37ff70f2a0906be49434965a10c051b2e26f0e7a7158624f00d35c6285fe89f1866eba7d89712baceb86286a08ea97dfe305c2ec3da506e834126fef885f095053a6eb50611caa912a094bd577d5ea62dd0efcfb4efb181b1cd8e06d5b550c41a42cb3bc2c7ef901915d5ec26ff70321e3caa80093da0fe2c18d23dff28411e1ea9818c5efa2e0f0f7c53cf262dd8dafb90ef2204b684f4a89262661df38beb80fbbcbfff209bfc62a2c03ad1f4b885f", result);

        source = "b00d00fdc25fb1a3e354527393e0d2ad37ff70f2a0906be49434965a10c051b2e26f0e7a7158624f00d35c6285fe89f1866eba7d89712baceb86286a08ea97dfe305c2ec3da506e834126fef885f095053a6eb50611caa912a094bd577d5ea62dd0efcfb4efb181b1cd8e06d5b550c41a42cb3bc2c7ef901915d5ec26ff70321e3caa80093da0fe2c18d23dff28411e1ea9818c5efa2e0f0f7c53cf262dd8dafb90ef2204b684f4a89262661df38beb80fbbcbfff209bfc62a2c03ad1f4b885f";
        result = AESCryptTool.decrypt(source);
        assertEquals("{\"adjustment\":null,\"festival\":null,\"festivalCommon\":null,\"tools\":null,\"config\":null,\"time\":1535442087,\"info\":{\"status\":\"200\",\"message\":\"数据请求成功\",\"time\":\"2018-08-28\"}}", result);
    }
}
