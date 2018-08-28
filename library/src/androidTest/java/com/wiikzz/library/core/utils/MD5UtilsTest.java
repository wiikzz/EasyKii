package com.wiikzz.library.core.utils;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by wiikii on 2018/8/28.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
@RunWith(AndroidJUnit4.class)
public class MD5UtilsTest {

    @Test
    public void testMd5ForString() {
        String source = "QNKCDZO";
        String md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e830400451993494058024219903391");

        source = "s878926199a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e545993274517709034328855841020");

        source = "s155964671a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e342768416822451524974117254469");

        source = "s214587387a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e848240448830537924465865611904");

        source = "s878926199a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e545993274517709034328855841020");

        source = "s1091221200a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e940624217856561557816327384675");

        source = "s1885207154a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e509367213418206700842008763514");

        source = "s1502113478a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e861580163291561247404381396064");

        source = "s1885207154a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e509367213418206700842008763514");

        source = "s1836677006a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e481036490867661113260034900752");

        source = "s155964671a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e342768416822451524974117254469");

        source = "s1184209335a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e072485820392773389523109082030");

        source = "s1665632922a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e731198061491163073197128363787");

        source = "s1502113478a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e861580163291561247404381396064");

        source = "s1836677006a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e481036490867661113260034900752");

        source = "s1091221200a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e940624217856561557816327384675");

        source = "s155964671a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e342768416822451524974117254469");

        source = "s1502113478a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e861580163291561247404381396064");


        source = "s155964671a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e342768416822451524974117254469");

        source = "s1665632922a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e731198061491163073197128363787");

        source = "s155964671a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e342768416822451524974117254469");

        source = "s1091221200a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e940624217856561557816327384675");

        source = "s1836677006a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e481036490867661113260034900752");

        source = "s1885207154a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e509367213418206700842008763514");

        source = "s532378020a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e220463095855511507588041205815");

        source = "s878926199a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e545993274517709034328855841020");

        source = "s1091221200a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e940624217856561557816327384675");

        source = "s214587387a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e848240448830537924465865611904");

        source = "s1502113478a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e861580163291561247404381396064");

        source = "s1091221200a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e940624217856561557816327384675");

        source = "s1665632922a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e731198061491163073197128363787");

        source = "s1885207154a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e509367213418206700842008763514");

        source = "s1836677006a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e481036490867661113260034900752");

        source = "s1665632922a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e731198061491163073197128363787");

        source = "s878926199a";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "0e545993274517709034328855841020");

        source = "0ca175b9c0f726a831d895e269332461";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "dad52b5719e3202e32a6619e14d0ccec");

        source = "{\"adjustment\":null,\"festival\":null,\"festivalCommon\":null,\"tools\":null,\"config\":null,\"time\":1535442087,\"info\":{\"status\":\"200\",\"message\":\"数据请求成功\",\"time\":\"2018-08-28\"}}";
        md5 = MD5Utils.getMd5ForString(source);
        assertEquals(md5, "b263f6b59c6e20f02b36c9a6bd9c06b4");
    }
}
