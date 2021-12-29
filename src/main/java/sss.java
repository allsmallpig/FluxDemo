import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2021/11/2 11:15
 * @description
 */
public class sss {

    public static void main(String[] args) throws IOException {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(6), LocalTime.MIN);
        System.out.println("startTime = " + startTime);
        LocalDateTime endTime =LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.MAX);
        System.out.println("endTime = " + endTime);

        final LocalDate now = LocalDate.now();
        LocalDateTime of = LocalDateTime.of(now.minusDays(4), LocalTime.MIN);
        System.out.println("\nof = " + of);
        LocalDateTime of1 = LocalDateTime.of(now.minusDays(1), LocalTime.MAX);
        System.out.println("of1 = " + of1);

        String ss = "{\n" +
                "    \"networkId\": 22,\n" +
                "    \"networkName\": \"总部\",\n" +
                "    \"franchiseeNetworkId\": null,\n" +
                "    \"franchiseeNetworkName\": null,\n" +
                "    \"agencyNetworkId\": null,\n" +
                "    \"agencyNetworkName\": null,\n" +
                "    \"statisticsDate\": \"2021-03-22\",\n" +
                "    \"complaintsCount\": 1,\n" +
                "    \"timeoutCount\": 0,\n" +
                "    \"autoCloseCount\": 0\n" +
                "  }";
        System.out.println("ss = " + ss.length());
    }
}
