package com.example.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Filename: FluxDemo /com.example.demo.test
 * @Description:
 * 参考 https://blog.csdn.net/y_k_y/article/details/84633001
 *
 *
 *
 * @Author: Tiezhu.Li@paat.com/804082784@qq.com
 * @Version: 1.0
 * @CreateTime: 2019/7/25 17:33
 * @History:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    public String name;
    public int age;
    public int type;

    public static void main(String[] args) throws FileNotFoundException {

//
//        List<String> list = new ArrayList<>();
//        Stream<String> stream = list.stream(); //获取一个顺序流
//        Stream<String> parallelStream = list.parallelStream(); //

        // 将数组转成流
        Integer[] nums = new Integer[10];
        Stream<Integer> streamss = Arrays.stream(nums);


        //用Stream中的静态方法：of()、iterate()、generate()
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6);
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream2.forEach(System.out::println); // 0 2 4 6 8 10
        Stream<Double> stream3 = Stream.generate(Math::random).limit(2);
        stream3.forEach(System.out::println);

        // BufferedReader.lines() 方法，将每行内容转成流
        BufferedReader reader = new BufferedReader(new FileReader("F:\\test_stream.txt"));
        Stream<String> lineStream = reader.lines();
        lineStream.forEach(System.out::println);

        //使用 Pattern.splitAsStream() 方法，将字符串分隔成流
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);

        //         筛选与切片
        //        filter：过滤流中的某些元素
        //        limit(n)：获取n个元素
        //        skip(n)：跳过n元素，配合limit(n)可实现分页
        //        distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
        Stream<Integer> streamaaa = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
        Stream<Integer> newStream = streamaaa.filter(s -> s > 5) //6 6 7 9 8 10 12 14 14
                .distinct() //6 7 9 8 10 12 14
                .skip(2) //9 8 10 12 14
                .limit(2); //9 8
        newStream.forEach(System.out::println);

       //映射
        //        map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        //        flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        List<String> list = Arrays.asList("a,b,c", "1,2,3");

        //将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",", ""));
        s1.forEach(System.out::println); // abc  123

        Stream<String> s3 = list.stream().flatMap(s -> {
            //将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        s3.forEach(System.out::println); // a b c 1 2 3








//
//        Student s1 = new Student("aa", 10, 1);
//        Student s2 = new Student("bb", 20, 2);
//        Student s3 = new Student("cc", 10, 3);
//        List<Student> list = Arrays.asList(s1, s2, s3);
//
//        //装成list
//        List<Integer> ageList = list.stream().map(Student::getAge).collect(Collectors.toList()); // [10, 20, 10]
//
//            //转成set
//        Set<Integer> ageSet = list.stream().map(Student::getAge).collect(Collectors.toSet()); // [20, 10]
//        //转成map,注:key不能相同，否则报错
//        Map<String, Integer> studentMap = list.stream().collect(Collectors.toMap(Student::getName, Student::getAge)); // {cc=10, bb=20, aa=10}
//
//            //字符串分隔符连接
//        String joinName = list.stream().map(Student::getName).collect(Collectors.joining(",", "(", ")")); // (aa,bb,cc)
//
//        //聚合操作
//        //1.学生总数
//        Long count = list.stream().collect(Collectors.counting()); // 3
//        //2.最大年龄 (最小的minBy同理)
//        Integer maxAge = list.stream().map(Student::getAge).collect(Collectors.maxBy(Integer::compare)).get(); // 20
//        //3.所有人的年龄
//        Integer sumAge = list.stream().collect(Collectors.summingInt(Student::getAge)); // 40
//        //4.平均年龄
//        Double averageAge = list.stream().collect(Collectors.averagingDouble(Student::getAge)); // 13.333333333333334
//        // 带上以上所有方法
//        DoubleSummaryStatistics statistics = list.stream().collect(Collectors.summarizingDouble(Student::getAge));
//        System.out.println("count:" + statistics.getCount() + ",max:" + statistics.getMax() + ",sum:" + statistics.getSum() + ",average:" + statistics.getAverage());
//
//        //分组
//        Map<Integer, List<Student>> ageMap = list.stream().collect(Collectors.groupingBy(Student::getAge));
//        //多重分组,先根据类型分再根据年龄分
//        Map<Integer, Map<Integer, List<Student>>> typeAgeMap = list.stream().collect(Collectors.groupingBy(Student::getType, Collectors.groupingBy(Student::getAge)));
//
//        //分区
//        //分成两部分，一部分大于10岁，一部分小于等于10岁
//        Map<Boolean, List<Student>> partMap = list.stream().collect(Collectors.partitioningBy(v -> v.getAge() > 10));
//
//        //规约
//        Integer allAge = list.stream().map(Student::getAge).collect(Collectors.reducing(Integer::sum)).get(); //40

    }
}
