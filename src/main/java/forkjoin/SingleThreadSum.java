package forkjoin;


import constant.NumberEnum;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2022-08-21 19:00
 * @description
 */
public class SingleThreadSum {


    /**
     * 单线程执行累加
     */

//    public static void main(String[] args) {

//        int[] array= AddArray.make();
//        int sum=0;
//        System.out.println("开始计时!");
//        long recordTime=System.currentTimeMillis();
//        for (int i = 0; i < array.length; ++i) {
//            sum+=array[i];
//        }
//        System.out.println("数组累加最终结果:"+sum+",耗时时长:"+(System.currentTimeMillis()-recordTime)+"ms");


//        boolean ss =  Integer.valueOf(124010041) == 124010041;
//        System.out.println("args = " + ss);
//
//    }



    public static void main(String[] args) {

        //如果是周末，不执行；
        //并且加时间
        //创建时间加间隔时间，然后倒着去减掉时间，检索中间的星期天和星期六

        LocalDateTime now = LocalDateTime.now();


        //TODO 配置的间隔时间
        int daysPlus = 8;

        int totalDays = 0;
        for (int i = 1; i <= daysPlus; i++) {
            LocalDateTime ldt = now.minusDays(i);
            Integer week = ldt.getDayOfWeek().getValue();
            //TODO 配置的第几个
            if((null!=week && week.equals(NumberEnum.SIX.getCode()))||
                    null!=week && week.equals(NumberEnum.SEVEN.getCode())){
                totalDays = totalDays + 1;
                System.out.println("dayOfWeek = " + ldt);
            }
        }
        System.out.println("totalDays = " + totalDays);
    }

    /**
     * 获取某时间段内所有周的列表（开始时间，结束时间）
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String[]> getWeekList(Date startDate, Date endDate){
        List<String[]> weekList = new ArrayList<>();
        //转换成joda-time的对象
        DateTime firstDay = new DateTime(startDate).dayOfWeek().withMinimumValue();
        DateTime lastDay = new DateTime(endDate).dayOfWeek().withMaximumValue();
        //计算两日期间的区间天数
        Period p = new Period(firstDay, lastDay, PeriodType.days());
        int days = p.getDays();
        if (days > 0){
            int weekLength = 7;
            for(int i=0;i<days;i=i+weekLength){
                String monDay = firstDay.plusDays(i).toString("yyyy-MM-dd");
                String sunDay = firstDay.plusDays(i+6).toString("yyyy-MM-dd");
                String [] week = {monDay,sunDay};
                weekList.add(week);
            }
        }
        return weekList;
    }

}
