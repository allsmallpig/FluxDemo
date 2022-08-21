package jdk8Practise.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2022-07-11 14:40
 * @description
 */
public class Coupon {
    private Integer couponId;
    private Integer price;
    private String name;
    public Coupon(Integer couponId, Integer price, String name) {
        this.couponId = couponId;
        this.price = price;
        this.name = name;
    }
    public Integer getCouponId() {
        return couponId;
    }
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
//        List<Coupon> couponList = new ArrayList<>();
//        Coupon coupon1 = new Coupon(1,100,"优惠券1");
//        Coupon coupon2 = new Coupon(2,200,"优惠券2");
//        Coupon coupon3 = new Coupon(3,300,"优惠券3");
//        Coupon coupon4 = new Coupon(3,400,"优惠券4");
//        couponList.add(coupon1);
//        couponList.add(coupon2);
//        couponList.add(coupon3);
//        couponList.add(coupon4);
//        Map<Integer, Coupon> resultList = couponList.stream().collect(Collectors.groupingBy(Coupon::getCouponId,Collectors.collectingAndThen(Collectors.toList(), value->value.get(0))));
//        System.out.println(JSON.toJSONString(resultList, SerializerFeature.PrettyFormat));

        System.out.println("equals = " + Long.valueOf(null));
    }

}