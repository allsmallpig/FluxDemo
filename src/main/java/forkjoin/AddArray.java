package forkjoin;

import java.util.Random;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2022-08-21 19:00
 * @description
 */
public class AddArray {
    //数组最大长度
    public static int arrayLength=3000000;
    //任务拆分最小单位,拆分到这个单位就不能拆分了,即阈值
    public static int splitMixThreshold=(arrayLength/10);


    public static int[] make(){
        int[] origin=new int[arrayLength];
        Random random=new Random();
        for (int i = 0; i < arrayLength; ++i) {
            origin[i]=random.nextInt(arrayLength*3);
        }
        return origin;
    }

    public static void main(String[] args) {

    }
}
