package vertx;


import cn.hutool.core.io.FileUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentMap;


/**
 * @author ltz
 * @version V1.0
 * @date 2020/10/30 16:26
 * @Description
 * @email goodmanalibaba@foxmail.com
 */
public class TestVertx {


    public static void main(String[] args) throws IOException {

        Cache<Object, Object> build = CacheBuilder.newBuilder().build();
        ConcurrentMap<Object, Object> objectObjectConcurrentMap = build.asMap();



        String path = "https://manwei00.oss-cn-shanghai.aliyuncs.com/zp/dev/职微你公众号二维码l.jpg";
        File file = FileUtils.getFile(path);

        path = path.substring(path.lastIndexOf("/")+1,path.length());
        System.out.println("path = " + path);
        BufferedInputStream inputStream = FileUtil.getInputStream(path);


        byte[] data = null;
        URL url = null;
        InputStream input = null;
        try{
            url = new URL(path);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            input = httpUrl.getInputStream();
        }catch (Exception e) {
            e.printStackTrace();

        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int numBytesRead = 0;
        while ((numBytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, numBytesRead);
        }
        data = output.toByteArray();
        output.close();
        input.close();



//        WebClient client = WebClient.create(Vertx.vertx());
//
//        long begin = System.currentTimeMillis();
//        client.getAbs("https://api.weixin.qq.com/sns/jscode2session").send(ar -> {
//            System.out.println("耗时: " + (System.currentTimeMillis() - begin));
//            if (ar.succeeded()) {
//                HttpResponse<Buffer> response = ar.result();
//                System.out.println("Received response with status code:" + response.statusCode());
//                System.out.println(response.bodyAsString());
//            } else {
//                System.out.println("Something went wrong " + ar.cause().getMessage());
//            }
//        });
        //  Vertx vertx = Vertx.vertx(); // (0)
        //        HttpClient client = vertx.createHttpClient(); // (1)
        //        client.getNow("www.baidu.com", "/", resp -> System.out.println("======" + resp.statusCode())); // (2)
        //
        //        TimeUnit.SECONDS.sleep(10); // (3)
    }

}
