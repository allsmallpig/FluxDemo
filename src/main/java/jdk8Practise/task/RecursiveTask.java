//package com.yl.css.api.service.impl.taskspecialaction;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Lists;
//import com.yl.css.api.model.vo.ProblemPiecePageVO;
//import lombok.extern.log4j.Log4j;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.ForkJoinTask;
//import java.util.concurrent.RecursiveTask;
//
///**
// * @author ltz
// * @version V1.0
// * @date 2022/07/11 16:17
// * @Description
// * @email goodmanalibaba@foxmail.com
// */
//@Slf4j
//public class ProblemPiecePageVORecursiveTask extends java.util.concurrent.RecursiveTask<List<ProblemPiecePageVO>> {
//
//    /**
//     * 每个"小任务"最多只打印70个数
//     */
//    private static final int MAX = 100;
//    private List<ProblemPiecePageVO> list;
//    public int start;
//    public int end;
//    private static List<ProblemPiecePageVO> listNew = new ArrayList();
//
//    public ProblemPiecePageVORecursiveTask(List<ProblemPiecePageVO> list, int start, int end) {
//        this.list = list;
//        this.start = start;
//        this.end = end;
//    }
//
//    @Override
//    protected List<ProblemPiecePageVO> compute() {
//        // 当end-start的值小于MAX时候，开始打印
//        int i = (end - start);
//        if (i < MAX) {
//            log.info("ProblemPiecePageVORecursiveTask执行任务end:{},start:{},i:{}",end,start,i);
//            for (ProblemPiecePageVO problemPiecePageVO : list) {
//
//                listNew.add(problemPiecePageVO);
//            }
//        }else{
//            int middle = (start + end) / 2;
//            ProblemPiecePageVORecursiveTask left = new ProblemPiecePageVORecursiveTask(list, start, middle);
//            ProblemPiecePageVORecursiveTask right = new ProblemPiecePageVORecursiveTask(list, middle+1, end);
//            left.fork();
//            right.fork();
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        List<ProblemPiecePageVO> list = Lists.newArrayListWithCapacity(1000);
//        Random random = new Random();
//        int total = 0;
//        // 初始化100个数字元素
//        for (int i = 0; i < 10000; i++) {
//            ProblemPiecePageVO wechatSendMessageRequestVo = new ProblemPiecePageVO();
////            wechatSendMessageRequestVo.setRemark(i+"序号");
//            list.add(wechatSendMessageRequestVo);
//        }
//        System.out.println("初始化时的总和=" + list.size());
//        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
//        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
//        // 提交可分解的PrintTask任务
//        forkJoinPool.invoke(new ProblemPiecePageVORecursiveTask(list, 0, list.size()));
//        // 关闭线程池
//        forkJoinPool.shutdown();
//    }
//}
