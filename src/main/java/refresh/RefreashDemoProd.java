package refresh;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2022-10-24 9:36
 * @description
 */
@Slf4j
public class RefreashDemoProd {

    public static void main(String[] args) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<String> strings = FileUtils.readLines(new File("C:\\Users\\litiezhu\\Desktop\\紫金山刷数据\\最终实际要刷的数据\\刷数据格式文本.txt"), "UTF-8");
        System.out.println("strings = " + strings.size());
        int i = 0;
        List<List<String>> partition = Lists.partition(strings, 20);
        for (List<String> stringList : partition) {
            for (String s : stringList) {
                s = s.replace("\t", "");
                System.out.println("i= " + i + "______s = " + s);
                extracted(s);
                i++;
            }
            try {
                Thread.sleep(2000);
                System.out.println("i= " + i + "______sllep over ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        stopWatch.stop();
        log.info("总共耗时",stopWatch.getTotalTimeMillis());
    }

    private static void extracted(String param) {
        //请求路径
        String url = "http://pro-cssjob.jms.com/jobinfo/trigger";
        OkHttpClient client = new OkHttpClient();
        String anString = "";
//        String cookie = "OUTFOX_SEARCH_USER_ID_NCOO=707812215.5230199; XXL_JOB_LOGIN_IDENTITY=3166376263353661383735356339336634306132306562323331346463613266; ___rl__test__cookies=1666575045429";
        String cookie = "OUTFOX_SEARCH_USER_ID_NCOO=707812215.5230199; XXL_JOB_LOGIN_IDENTITY=6533636261333836626562333166643831613365663537333832353933333932; ___rl__test__cookies=1666590181146";
        //组装键值，params为键值，name为属性名
        FormBody formBody = new FormBody.Builder()
                //需要的参数(key,value的格式可以一直add)
                .add("id", "325")
                .add("executorParam", param)
//            .add("offset", "0")
//            .add("limit", "10000")
                .build();
        //组装请求头
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                //设置请求cookie(如果当前调用的是登录请求去获取cookie就不需要这个)
                .addHeader("Cookie", cookie)
                .build();
        //该方法容易触发IOException异常
        try {
            //获取返回值
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //获取当前请求的cookie
//            if (response.isSuccessful()) {//response 请求成功
//                Headers headers = response.headers();
//                List<String> cookies = headers.values("Set-Cookie");
//                if (cookies.size() > 0) {
//                    String session = cookies.get(0);
//                    if (!StringUtils.isBlank(session)) {
//                        int size = session.length();
//                        int i = session.indexOf(";");
//                        if (i < size && i >= 0) {
//                            //最终获取到的cookie
//                            cookie = session.substring(0, i);
//                        }
//                    }
//                }
//            }
            if (response.isSuccessful()) {
                try {
                    anString += response.body().string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                anString += "error!";
            }
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

}
