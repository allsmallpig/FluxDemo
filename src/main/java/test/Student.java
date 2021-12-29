package test;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2021/11/11 15:37
 * @description
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    String name;
    String title;

    public static void main(String[] args) {
//        LocalDate startDate = LocalDate.now();
//     // +8是北京时间
//        LocalDateTime of = LocalDateTime.of(startDate, LocalTime.MIN);
//
//        System.out.println(of);
//
//
//        LocalDateTime endTime = LocalDateTime.of(startDate.minusDays(4), LocalTime.MAX).minusDays(1);
//        System.out.println("endTime = " + endTime);
//        LocalDateTime startTime =  LocalDateTime.of(startDate.plusDays(Long.valueOf(-15)),LocalTime.MIN);
//        System.out.println("startTime = " + startTime);
//        //查询T-1 --->T-4天的数据分页去更新
//        final LocalDate now = LocalDate.now();
//        LocalDateTime with = LocalDateTime.of(now.minusDays(1), LocalTime.MAX);
//        System.out.println("with = " + with);

        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.MIN);
        LocalDateTime endTime =LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.MAX);
        System.out.println("endTime = " + endTime);

        System.out.println("endTime = " + StringUtils.isNotBlank(null));

        LocalDateTime of = LocalDateTime.of(LocalDate.now().minusDays(4), LocalTime.MIN);
        System.out.println("of = " + of);
        LocalDateTime of1 = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);
        System.out.println("of1 = " + of1);

        Gson gson = new Gson();
        String s = gson.toJson(null);
        System.out.println("s = " + s);

        System.out.println("s = " + StringUtils.isBlank(""));
        Student student = new Student();
        student.setTitle("");


    }
}


