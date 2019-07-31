package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/")
@SpringBootApplication
public class DemoApplication {

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

//        float a = 1.0f - 0.9f;
//        float b = 0.9f - 0.8f;
//        if (a == b) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }

//        Float a = Float.valueOf(1.0f - 0.9f);
//        Float a = Float.valueOf("10")-Float.valueOf("9");
//        Float b = Float.valueOf("9")-Float.valueOf("8");
//        Float b = Float.valueOf(0.9f - 0.8f);
//        if (a.equals(b)) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
//
//        String param = null;
//        switch (param) {
//            case "null:
//                System.out.println("null");
//                break;
//            default:
//                System.out.println("default");
//        }

        BigDecimal a = new BigDecimal(0.1);
        System.out.println(a);
        BigDecimal bigDecimal = new BigDecimal("0.1");
        BigDecimal bigDecimal1 = new BigDecimal("0.0");
        BigDecimal b = bigDecimal.subtract(bigDecimal1);
        System.out.println(b);
        try {
            lock.tryLock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


//        SpringApplication.run(DemoApplication.class, args);
//        List words="Hello,World".toCharArray();
//        words.iterator().forEachRemaining();
//        words.stream()
//                .map(word -> word.split(""))
//                .distinct()
//                .collect(toList());
    }

    @GetMapping("/test")
    public String etr() {
        return "0000";
    }

//    public String get(){
//
//    }


}
