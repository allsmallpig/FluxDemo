package callback.lerandemo.futures;

/**
 * @author
 * @packageName netty.lerandemo.futures
 * @date 2020/1/12 11:46
 * @Description
 * @Versin 1.0
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class FutureExample {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                //do something
                System.out.println("i am task1.....");
            }
        };
        Callable<Integer> task2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                //do something
                return new Integer(100);
            }
        };
        Future<?> f1 = executor.submit(task1);
        Future<Integer> f2 = executor.submit(task2);
        System.out.println("task1 is completed? " + f1.isDone());
        System.out.println("task2 is completed? " + f2.isDone());
        //waiting task1 completed
        while (f1.isDone()) {
            System.out.println("task1 completed.");
            break;
        }
        //waiting task2 completed
        while (f2.isDone()) {
            System.out.println("return value by task2: " + f2.get());
            break;
        }
    }


}
