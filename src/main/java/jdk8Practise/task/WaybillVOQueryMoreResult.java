package jdk8Practise.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2019
 *
 * @author linzijing
 * @since Created in 2019/6/19 17:05
 */
@Data
public class WaybillVOQueryMoreResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * (value = "1 一段码 2 二段码")
     */
    private Integer segmentCodeType;

    /**
     * (value = "1 一段码 2 二段码")
     */
    private String segmentCode;


    /**
     * (value = "末端交货网点id")
     */
    private Integer networkId;

    /**
     * (value = "末端交货网点名称")
     */
    private String networkName;


    /**
     * (name = "deliveryDistributionCenterId", value = "末端交货转运中心id")
     */
    private Integer deliveryDistributionCenterId;

    /**
     * (name = "deliveryDistributionCenterId", value = "末端交货转运中心名称")
     */
    private Integer deliveryDistributionCenterName;

    public static void main(String[] args) {
//        Long id  = Long.valueOf("411729465886900247");
//        System.out.println("args = " + id%32);
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime firstday = now.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
//        System.out.println("firstday = " + firstday);
//
//
//        LocalDateTime firstDayOfMonth = LocalDateTime.of(LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN.withSecond(0));
//        firstDayOfMonth = firstDayOfMonth.withMinute(0);
//        System.out.println("firstday = " + firstDayOfMonth);
//
//        DateTime dateTime = DateUtil.beginOfMonth(new Date());
//        System.out.println("dateTime = " + dateTime);
        System.out.println("args = " + Objects.equals(null,1));
    }
}

