package task;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author ltz
 * @version V1.0
 * @date 2020/12/22 16:17
 * @Description
 *  可以参考 https://blog.csdn.net/m200674067/article/details/80417278
 * @email goodmanalibaba@foxmail.com
 */
public class RecursiveTask extends java.util.concurrent.RecursiveTask<WechatSendMessageRequestVo> {

    /**
     * 每个"小任务"最多只打印70个数
     */
    private static final int MAX = 100;
    private List<WechatSendMessageRequestVo> list;
    private int start;
    private int end;

    public RecursiveTask(List<WechatSendMessageRequestVo> list, int start, int end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @Override
    protected WechatSendMessageRequestVo compute() {
        // 当end-start的值小于MAX时候，开始打印
        int i = (end - start);
        if (i < MAX) {
            System.out.println("i = " + i);
            System.out.println("end = " + end);
            System.out.println("start = " + start);
            System.err.println("====执行======"+ i);
            for (WechatSendMessageRequestVo wechatSendMessageRequestVo : list) {
                System.out.println("wechatSendMessageRequestVo = " + JSON.toJSONString(wechatSendMessageRequestVo));
            }
        }else{
            System.out.println("i = " + i);
            System.err.println("=====任务分解======");
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            RecursiveTask left = new RecursiveTask(list, start, middle);
            RecursiveTask right = new RecursiveTask(list, middle+1, end);
            System.err.println("=====任务分解===start==="+ start+"====middle===="+ middle+"==end=="+ end+"==");
            // 并行执行两个小任务
            ForkJoinTask<WechatSendMessageRequestVo> fork = left.fork();
            ForkJoinTask<WechatSendMessageRequestVo> fork1 = right.fork();

        }
        return null;
    }

    public static void main(String[] args) {
        List<WechatSendMessageRequestVo> list = Lists.newArrayListWithCapacity(1000);
        Random random = new Random();
        int total = 0;
        // 初始化100个数字元素
        for (int i = 0; i < 10000; i++) {
            WechatSendMessageRequestVo wechatSendMessageRequestVo = new WechatSendMessageRequestVo();
            wechatSendMessageRequestVo.setRemark(i+"序号");
            list.add(wechatSendMessageRequestVo);
        }
        System.out.println("初始化时的总和=" + list.size());
        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        // 提交可分解的PrintTask任务
        forkJoinPool.invoke(new RecursiveTask(list, 0, list.size()));
        // 关闭线程池
        forkJoinPool.shutdown();
    }
}
