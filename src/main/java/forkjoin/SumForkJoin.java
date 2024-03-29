package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2022-08-21 19:17
 * @description
 */
public class SumForkJoin {

    /** 创建ForkJoin框架  递归进入任务拆分,递归出来任务结果累加 **/
    static class SumTask extends RecursiveTask<Integer> {

        int[] recursiveArray;
        //划分开始位置
        int form;
        //划分结束位置
        int to;

        public SumTask(int[] recursiveArray, int form, int to) {
            this.recursiveArray = recursiveArray;
            this.form = form;
            this.to = to;
        }

        @Override
        protected Integer compute() {
            //判断当前数组最小长度阈值,没有达到继续拆分
            if((to-form)< AddArray.splitMixThreshold){
                //满足阈值的数组处理,累加
                int count= 0;
                for(int i=form;i<to;++i){
                    count+=recursiveArray[i];
                }
                return count;
            }else{
                //数组长度划半
                int half=(form+to)/2;
                //递归拆分
                SumTask left=new SumTask(recursiveArray,form,half);
                SumTask right=new SumTask(recursiveArray,half+1,to);
                invokeAll(left,right);
                //等待最小阈值求和完毕在累加返回给上一层Join任务
                return left.join()+right.join();
            }
        }
    }


    public static void main(String[] args) {

        ForkJoinPool forkJoinPool=new ForkJoinPool();
        int[] array=AddArray.make();
        SumTask sumTask=new SumTask(array,0,array.length-1);
        Long recordTime=System.currentTimeMillis();
        forkJoinPool.submit(sumTask);
        System.out.println("数组累加最终结果:"+sumTask.join()+",耗时时长:"+(System.currentTimeMillis()-recordTime)+"ms");

    }

}
