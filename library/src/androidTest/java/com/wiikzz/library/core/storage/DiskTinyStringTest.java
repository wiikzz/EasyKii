package com.wiikzz.library.core.storage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class DiskTinyStringTest {

    private static final String SAVED_JSON = "{\"ver\":\"466.0\",\"data\":[{\"icon\":\"" +
            "http://www.77tianqi.com/taibaiimg/calendar/images/upload/5b4f5841af6e0.png\"," +
            "\"title\":\"大肚笑佛 消灾除病\",\"desc\":\"聚福聚财 消灾除病\",\"url\":\"" +
            "http://www.77tianqi.com/frame/fpMall/detail?pid=6&from=aztuwen&wnl=Nj1eGkw7IS5eMA%3D%3D\"," +
            "\"group\":\"1\",\"priority\":\"5\",\"start\":1512057600,\"end\":1546223376,\"updatetime\":1531927461," +
            "\"type\":\"web\",\"code\":\"\",\"deepLink\":\"\",\"pos\":\"7\",\"package\":\"\",\"duration\":\"5\"}," +
            "{\"icon\":\"http://www.77tianqi.com/taibaiimg/calendar/images/upload/5b4f58d35e863.jpg\"," +
            "\"title\":\"你是富贵命？劳碌命？\",\"desc\":\"立即测算\",\"url\":\"http://www.77tianqi.com/frame/sm/bazi?from=calendar_aztuwen&wnl=Nj1eGkw7IS5eMA%3D%3D\"," +
            "\"group\":\"1\",\"priority\":\"6\",\"start\":1512057600,\"end\":1546223376,\"updatetime\":1535613222,\"type\":\"web\",\"code\":\"\"," +
            "\"deepLink\":\"\",\"pos\":\"7\",\"package\":\"\",\"duration\":\"5\"},{\"icon\":\"http://www.77tianqi.com/taibaiimg/calendar/images/upload/5b4f591629300.png\"," +
            "\"title\":\"2018狗年运势全面揭晓！\",\"desc\":\"下半年运势立即测算\",\"url\":\"http://www.77tianqi.com/frame/sm/2018?from=calendar_aztuwen&wnl=Nj1eGkw7IS5eMA%3D%3D\"," +
            "\"group\":\"1\",\"priority\":\"7\",\"start\":1512057600,\"end\":1546223376,\"updatetime\":1531927472,\"type\":\"web\",\"code\":\"\",\"deepLink\":\"\",\"pos\":" +
            "\"7\",\"package\":\"\",\"duration\":\"5\"},{\"type\":\"web\",\"code\":\"zhougongjiemeng\"," +
            "\"icon\":\"http://www.77tianqi.com/taibaiimg/calendar/images/upload/5be0f2650972a.gif\",\"title\":" +
            "\"姻缘\",\"start\":1539313289,\"startSelect\":1539100800,\"end\":1698721291,\"endSelect\":1667231999,\"desc\":" +
            "\"周公解梦\",\"url\":\"http://www.77tianqi.com/frame/sm/yinyuantq?from=calendar_azpic&wnl=Nj1eGkw7IS5eMA%3D%3D\"," +
            "\"deepLink\":\"\",\"pos\":\"1\",\"shareTitle\":\"\",\"shareContent\":\"\",\"shareHead\":\"\"},{\"type\":\"web\",\"code\":" +
            "\"十年大运\",\"icon\":\"http://www.77tianqi.com/taibaiimg/calendar/images/upload/5be10ef10fcdb.gif\",\"title\":\"十年大运\"," +
            "\"start\":1506403940,\"startSelect\":-28800,\"end\":1603690349,\"endSelect\":57599,\"desc\":\"\",\"url\":" +
            "\"http://www.77tianqi.com/frame/sm/tenyear?from=calendar_azpic&wnl=Nj1eGkw7IS5eMA%3D%3D\",\"deepLink\":\"\",\"pos\":" +
            "\"3\",\"shareTitle\":\"\",\"shareContent\":\"\",\"shareHead\":\"\"},{\"type\":\"web\",\"code\":\"SDK2-姓名\",\"icon\":" +
            "\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541502793470&di=b80960799b0740a9097151" +
            "611b447417&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160304%2Fmp61773703_1457053171645_4.gif\",\"title\":" +
            "\"真人大师\",\"start\":1506405418,\"startSelect\":1525708800,\"end\":1601099821,\"endSelect\":1541001599,\"desc\":" +
            "\"\",\"url\":\"http://www.77tianqi.com/frame/realMaster/entrance?from=test&wnl=Nj1eGkw7IS5eMA%3D%3D\",\"deepLink\":" +
            "\"\",\"pos\":\"1\",\"shareTitle\":\"权威大师，语音解惑\",\"shareContent\":\"财运、事业、健康、姻缘都能算！世界顶级命理专家团队太白文化全力打造\"," +
            "\"shareHead\":\"资深大师解惑，电话直连\"},{\"type\":\"web\",\"code\":\"SDK2-姻缘\",\"icon\":" +
            "\"http://www.77tianqi.com/taibaiimg/calendar/images/upload/5b4f5eed04309.png\",\"title\":\"八字精批\"," +
            "\"start\":1506405273,\"startSelect\":1526140800,\"end\":1601099675,\"endSelect\":1704211199,\"desc\":" +
            "\"dddd\",\"url\":\"http://www.77tianqi.com/frame/sm/bazi?from=calendar_azpic&wnl=Nj1eGkw7IS5eMA%3D%3D\"," +
            "\"deepLink\":\"\",\"pos\":\"2\",\"shareTitle\":\"\",\"shareContent\":\"\",\"shareHead\":\"\"},{\"type\":\"web\"," +
            "\"code\":\"苹果-姻缘\",\"icon\":\"http://www.77tianqi.com/taibaiimg/calendar/images/upload/5b4f5f5a85474.png\",\"title\":" +
            "\"命中姻缘\",\"start\":1504687570,\"startSelect\":-28800,\"end\":1599381971,\"endSelect\":57599,\"desc\":\"\",\"url\":" +
            "\"http://www.77tianqi.com/frame/sm/yinyuantq?from=calendar_azpic&wnl=Nj1eGkw7IS5eMA%3D%3D\",\"deepLink\":\"\",\"pos\":\"4\"," +
            "\"shareTitle\":\"\",\"shareContent\":\"\",\"shareHead\":\"\"},{\"type\":\"web\",\"code\":\"苹果-前世\",\"icon\":" +
            "\"http://www.77tianqi.com/taibaiimg/calendar/images/upload/5b4f5f715ed17.png\",\"title\":\"姓名解密\",\"start\":1504687360," +
            "\"startSelect\":1531929600,\"end\":1599381761,\"endSelect\":1564761599,\"desc\":\"\",\"url\":" +
            "\"http://www.77tianqi.com/frame/sm/xingming?from=calendar_azpic&wnl=Nj1eGkw7IS5eMA%3D%3D\",\"deepLink\":\"\"," +
            "\"pos\":\"5\",\"shareTitle\":\"\",\"shareContent\":\"\",\"shareHead\":\"\"},{\"type\":\"web\",\"code\":\"\",\"icon\":" +
            "\"http://www.77tianqi.com/taibaiimg/calendar/images/upload/5b4f5f900c5d4.png\",\"title\":\"前世今生\",\"start\":1531968450," +
            "\"startSelect\":1531929600,\"end\":1595126852,\"endSelect\":1563551999,\"desc\":\"\",\"url\":" +
            "\"http://www.77tianqi.com/frame/sm/qianshi?from=calendar_azpic&wnl=Nj1eGkw7IS5eMA%3D%3D\",\"deepLink\":\"\"," +
            "\"pos\":\"6\",\"shareTitle\":\"\",\"shareContent\":\"\",\"shareHead\":\"\"},{\"type\":\"web\",\"code\":\"SDK2-姓名\"," +
            "\"icon\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541502793470&di=b80960799" +
            "b0740a9097151611b447417&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160304%2Fmp61773703_1457053171645_4.gif\"," +
            "\"title\":\"真人大师\",\"start\":1506405418,\"startSelect\":1525708800,\"end\":1601099821,\"endSelect\":1541001599,\"desc\":" +
            "\"\",\"url\":\"http://www.77tianqi.com/frame/realMaster/entrance?from=test&wnl=Nj1eGkw7IS5eMA%3D%3D\",\"deepLink\":\"\",\"pos\":" +
            "\"10\",\"shareTitle\":\"权威大师，语音解惑\",\"shareContent\":\"财运、事业、健康、姻缘都能算！世界顶级命理专家团队太白文化全力打造\",\"shareHead\":" +
            "\"资深大师解惑，电话直连\"},{\"type\":\"web\",\"code\":\"zhougongjiemeng\",\"icon\":\"https://timgsa.baidu.com/timg?image&quality=80&siz" +
            "e=b9999_10000&sec=1541502793470&di=b80960799b0740a9097151611b447417&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160304%2Fmp617" +
            "73703_1457053171645_4.gif\",\"title\":\"姻缘\",\"start\":1539313289,\"startSelect\":1539100800,\"end\":1698721291,\"endSelect" +
            "\":1667231999,\"desc\":\"周公解梦\",\"url\":\"http://www.77tianqi.com/frame/sm/yinyuantq?from=calendar_azpic&wnl=Nj1eGkw7IS5eMA%3D%3D" +
            "\",\"deepLink\":\"\",\"pos\":\"10\",\"shareTitle\":\"\",\"shareContent\":\"\",\"shareHead\":\"\"}],\"width\":990,\"height\":210}";

    private static final String SAVED_JSON_2 = "{\"ver\":\"466.0\",\"data\": null}";

    @Test
    public void testDiskTinyString() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        String storageKey = "key_string_json";
        DiskTinyString.putString(appContext, storageKey, SAVED_JSON);
        String a = DiskTinyString.getString(appContext, storageKey);
        assertNotNull(a);
        assertEquals(a.trim(), SAVED_JSON);

        DiskTinyString.putString(appContext, storageKey, SAVED_JSON_2);
        a = DiskTinyString.getString(appContext, storageKey);
        assertNotNull(a);
        assertEquals(a.trim(), SAVED_JSON_2);
    }

}
