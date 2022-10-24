package constant;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2019
 * 数字枚举
 *
 * @author wanghuizhen
 * @since Created in 2020-01-03
 */
public enum NumberEnum {
    NEGATIVE_ONE(-1),
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    FIFTEEN(15),
    THIRTY(30),
    THIRTY_ONE(31),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    THOUSAND(1000),
    TWO_THOUSAND(2000),
    ;
    private Integer code;

    NumberEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

//    public static void main(String[] args) {
//        String mobile = "55151588";
//        String maskStr = getStringBuffer(mobile);
//        System.out.println("length = " + maskStr);
//    }
//
//    private static String getStringBuffer(String mobile) {
//        if(StringUtils.isNotEmpty(mobile)){
//            StringBuffer maskStr = new StringBuffer();
//            int length = mobile.length();
//            for (int j = 0; j < length - 4; j++) {
//                maskStr.append("*");
//            }
//            maskStr.append(mobile, length - 4, length);
//            return maskStr.toString();
//        }
//       return "";
//    }

    public static void main(String[] args) {
//        String str = "{\"isConnected\":\"1\",\"timeRing\":\"17\"}";
//
//        byte[] bytes = str.getBytes();
//        System.out.println("bytes = " + bytes.length);

//        String collect = Stream.of("1", "2", "3").collect(Collectors.joining());
//        System.out.println("collect = " + collect);

//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime localDateTime = now.minusSeconds(500000);
//        Duration duration = Duration.between(now, localDateTime);
//        System.out.println("second = " + duration.abs().getSeconds());
//        System.out.println("second = " + duration.getSeconds());
        int i = 25;
        i = ++i;
        System.out.println("args = " + i);
    }





}
