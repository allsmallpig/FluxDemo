package com.practise.netty.test;

import groovy.util.logging.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

/**
 * @Filename: FluxDemo /com.example.demo.test
 * @Description:
 * @Author: Tiezhu.Li@paat.com/804082784@qq.com
 * @Version: 1.0
 * @CreateTime: 2019/6/17 20:35
 * @History:
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
//        Flux.just("Hello", "World").subscribe(System.out::println);
//        Flux.empty().subscribe(System.out::println);
//        Flux.range(1, 10).subscribe(System.out::println);
//        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
//        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
//        Mono.create(i->{
//
//        }).subscribe(System.out::println);

//        final Random random = new Random();
//        Flux.generate(ArrayList::new, (list, sink) -> {
//            int value = random.nextInt(100);
//            list.add(value);
//            sink.next(value);
//            if (list.size() == 10) {
//                sink.complete();
//            }
//            return list;
//        }).subscribe(System.out::println);
//
//         Flux.empty().subscribe(System.out::println);
//         long count = Flux.create(i -> {
//            for (int j = 0; j < 10; j++) {
//                i.next(j);
//            }
//            i.complete();
//        }).toStream().count();
//        System.out.println("count = " + count);
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int io = random.nextInt(10);
            System.out.println("i = " + io);
        }


//        CompletableFuture.supplyAsync(()->"Hello")
//                .thenApply(s->s+" world")
//                .thenApply(String::toUpperCase)
//                .thenCombine(CompletableFuture.completedFuture(" java "), (s1,s2)->s1+s2)
//                .thenAccept(System.out::println);
//
//
//        Flux.create(I->{
//
//            I.complete();
//            System.out.println("I = " + I);
//        });

//        Flux.just("a", "b")
//                .zipWith(Flux.just("c", "d"))
//                .subscribe(System.out::println);

//
//        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
//
//        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
//        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
//        Flux.range(1,10).subscribe(System.out::println);
//
//        long count = Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).toStream().count();
//        Mono<List<List<Integer>>> listMono = Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).collectList();


        Optional<Integer> max = Flux.just("0")
                .map(v -> {
                    return Integer.valueOf(v) * Integer.valueOf(v);
                })
                .toStream()
                .sorted()
                .max(Integer::compareTo);


        Integer integer = max.get();
        System.out.println("integer = " + integer);


        LocalTime now = LocalTime.now();
        LocalTime of = LocalTime.of(5, 15);
        Duration between = Duration.between(now, of);
        Duration betweens = Duration.between(of, now);
        System.out.println("betweens = " + betweens.toMinutes());
        long l = between.toMinutes();
        System.out.println(l);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add("swerge");
        objects.add("swerge");
        objects.add("swerge");
        objects.add("swerge");

        Collection<String> coll = new ArrayList<String>();
        String[] theStrings = new String[coll.size()];
        coll.toArray(theStrings);

    }


}
