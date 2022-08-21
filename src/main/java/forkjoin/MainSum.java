package forkjoin;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2022-08-21 19:00
 * @description
 */
public class MainSum {

    public static void main(String[] args) {

        int[] array= AddArray.make();
        int sum=0;
        System.out.println("开始计时!");
        long recordTime=System.currentTimeMillis();
        for (int i = 0; i < array.length; ++i) {
            sum+=array[i];
        }
        System.out.println("数组累加最终结果:"+sum+",耗时时长:"+(System.currentTimeMillis()-recordTime)+"ms");

    }

}
