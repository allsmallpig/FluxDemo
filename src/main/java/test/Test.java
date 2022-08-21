package test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.sql.Array;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2021/11/11 15:38
 * @description
 */
public class Test<T> {

    public static void main(String[] args) {
        List<String> small = Lists.newArrayList("10657","10656","10659","10658","10651","10650");
        List<String> big  = Lists.newArrayList("320001","10657","10656","10659","10658","320001019","320001016","320001017","10651","320001018","10650","10653","10652","10655","10654","10668","10667","10669","10660","10662","10661","10664","10663","10666","10665","1238591","6677889","10635","10634","10637","10636");

        Set differenceSet = Sets.difference(Sets.newHashSet(big), Sets.newHashSet(small));

        ArrayList arrayList = Lists.newArrayList(differenceSet);
        System.out.println("arrayList = " + arrayList);
    }





/*    public static void main(String[] args) {
        List<Integer> numbers = Stream.iterate(1, x -> x + 1).limit(10).collect(Collectors.toList());
//        Integer aa = 0;
//        for (Integer i : numbers) {
//            aa += i;
//        }
        Integer dd = numbers.stream().reduce(0, (a, b) -> a + b, (a, b) -> a - b);
//        Optional<Integer> dd1 = numbers.stream().reduce((a, b) -> a + b);
        System.out.println(new Gson().toJson(numbers));
//        System.out.println(aa);
        System.out.println(dd);
//        System.out.println(dd1.get());

        Scheduler trampoline = Schedulers.trampoline();
        trampoline.createWorker().schedule(() -> {

        }).dispose();

    }*/


}
