package adapter;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author cuibilin
 * @since Created in 2021/1/6 9:27
 */
public class DesensitizationUtil {

    public static String mobile(String mobile) {
        if (mobile == null || mobile.length() <= 3) {
            return mobile;
        } else if (mobile.length() < 11) {
            return StringUtils.left(mobile, 3).concat(mobile.substring(3).replaceAll(".", "*"));
        } else {
            return StringUtils.left(mobile, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(mobile, 4), StringUtils.length(mobile), "*"), "***"));
        }
    }


    public static String desensitization(String desensitization) {
        //小于16位直接返回
        if (desensitization == null || desensitization.length() < 16) {
            return desensitization;
        }
        //匹配连续的16位(数字|空格)
        String reg = "(\\d|\\s){16,}";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(desensitization);
        Set<String> set = new HashSet<>();
        while (matcher.find()) {
            set.add(matcher.group(0));
        }
        if (set.isEmpty()) {
            return desensitization;
        }
        //key 需要替换的字符串 value 脱敏后的字符串
        HashMap<String, String> map = new HashMap(set.size());
        for (String matcherStr : set) {
            //去掉空字符串
            String m = matcherStr.replaceAll(" ", "");
            int length = m.length();
            if (length > 16) {
                String r = m.substring(0, 4) + "******" + m.substring(m.length() - 4);
                map.put(matcherStr, r);
            }
        }
        if (map.size() > 0) {
            //对key进行排序，按照字符串长度判断
            ArrayList<String> lengthSortList = new ArrayList<>(map.keySet());
            lengthSortList.sort((a, b) -> {
                int lengthA = a.length();
                int lengthB = b.length();
                if (lengthA == lengthB) {
                    return 0;
                } else {
                    // 长度从长到短
                    return lengthA > lengthB ? -1 : 1;
                }
            });
            for (String key : lengthSortList) {
                desensitization = desensitization.replaceAll(key, map.get(key));
            }
        }
        return desensitization;
    }

    public static void main(String[] args) {
//        String m = "11111848";
//        String s = "**********************************************";
//        String r = s.substring(0, m.length() - 4) + m.substring(m.length() - 4);
//        System.out.println("desensitization = " + r);

        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(30), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().minusDays(30), LocalTime.MAX);
        System.out.println("startTime = " + startTime);
        System.out.println("endTime = " + endTime);
    }




}
