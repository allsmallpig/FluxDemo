package async.completableFuture;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author
 * @packageName main
 * @date 2020/1/16 11:08
 * @Description
 * @Versin 1.0
 */
public class Main {


    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
//        double abs = Math.abs(new BigDecimal("-0.01").doubleValue());
//        System.out.println("abs = " + abs);
//

//        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "wegr");
//        Thread.sleep(5000);
//        //立即执行
//        stringCompletableFuture.complete("23454");
        //抛异常
//        stringCompletableFuture.completeExceptionally(new Exception());
//        System.out.println(stringCompletableFuture.get());

        Observable<Object> objectObservable = Observable.fromFuture(CompletableFuture.runAsync(() -> {

        }).handleAsync((unused, throwable) -> null).whenComplete((o, throwable) -> {

        }));

        Observable<Integer> wrap = Observable.wrap(Observable.fromOptional(Optional.of(1)));
        wrap.subscribe();
        List<Integer> collect = wrap.blockingStream().collect(Collectors.toList());

        List<Object> collect1 = Collections.singletonList(Single.fromObservable(wrap)
                .flatMapPublisher(integer ->
                        (Publisher) subscriber -> {
        }).blockingStream().collect(Collectors.toList()));



        System.out.println("count = " + collect.size());



        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        //future.join();
        Integer integer = future.get();
        CompletableFuture<Integer> integerCompletableFuture = future.whenCompleteAsync(new BiConsumer<Integer, Throwable>() {
            @Override
            public void accept(Integer integer, Throwable throwable) {
             /*   if (!integer.equals(200)) {
                    try {
                        throw throwable;
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                } else */
                if (integer.equals(100)) {
                    integer = 1;
                }
            }
        });

        CompletableFuture.runAsync(() -> work("A2")).runAfterEither(CompletableFuture.runAsync(() -> work("B2")), () -> work("C2")).get();


        System.out.println("integerCompletableFuture = " + integerCompletableFuture.get());

        CompletableFuture<Void> voidCompletableFuture = integerCompletableFuture.thenAccept(System.out::println);
        System.out.println("voidCompletableFuture = " + voidCompletableFuture.get());

        CompletableFuture<Void> f = future.thenAcceptBoth(CompletableFuture.completedFuture(10), (x, y) -> System.out.println(x * y));
        System.out.println("thenAcceptBoth== " + f.get());

        CompletableFuture<Void> voidCompletableFutureThenRun = future.thenRun(() -> {
            try {
                System.out.println("future = " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("voidCompletableFutureThenRun = " + voidCompletableFutureThenRun.get());

//        CompletableFuture.anyOf()


        final CompletableFuture<Integer> fnew = BasicMain.compute();
        class Client extends Thread {
            CompletableFuture<Integer> f;

            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = fnew;
            }

            @Override
            public void run() {
                try {
                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        new Client("Client1", fnew).start();
        new Client("Client2", fnew).start();
        System.out.println("waiting");
        fnew.complete(100);
        //f.completeExceptionally(new Exception());
        System.in.read();
    }

    public static void work(String s) {
        System.out.println(s);
    }

    static class BasicMain {
        public static CompletableFuture<Integer> compute() {
            final CompletableFuture<Integer> future = new CompletableFuture<>();
            return future;
        }
    }


}
