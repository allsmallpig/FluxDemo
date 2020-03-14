package jdk8Practise.stream_practise;

import com.google.common.base.Function;
import com.google.common.collect.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private String s;
    private int i;
    private int ii;


    public static void main(String[] args) throws InterruptedException {
//        Integer convert = Test.CONVERT(1);
//        System.out.println("convert = " + convert);
//        Byte convert1 = Test.CONVERT((byte) 1);
//        System.out.println("convert1 = " + convert1);
//        String wet = Test.CONVERT("wet");
//        System.out.println("wet = " + wet);
//        Object convert2 = Test.CONVERT(null);
//        System.out.println("convert2 = " + convert2);
        Integer[] integers = Test.toArray(1);
        String[] aes = Test.toArray("aes");


        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(Arrays.asList("wegt4", "ewty").iterator(),
                new Function<String, Integer>() {
                    @Override
                    public Integer apply(String string) {
                        return string.length();
                    }
                });
        ImmutableCollection<String> values = stringsByIndex.values();

        ImmutableSet digits = ImmutableSet.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(String string) {
                return string.length();
            }
        };

        ImmutableListMultimap<Integer, String> digitsByLength = Multimaps.index(digits, lengthFunction);

        List<Test> ewy = Arrays.asList(new Test())
                .stream().
                        filter((Predicate<Test>) s -> Objects.nonNull(s))
                .parallel()
                .collect(Collectors.toList());

        Predicate<String> condition = new com.google.common.base.Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return ((String) input).startsWith("P");
            }
        };
//        boolean allIsStartsWithP = Iterators.all(list.iterator(), condition);
//        System.out.println("all result == " + allIsStartsWithP);


        final boolean wege = Iterators.any(Iterators.forArray("wege"), new com.google.common.base.Predicate<String>() {
            @Override
            public boolean apply(String string) {
                return !StringUtils.isEmpty(string);
            }
        });

        ArrayListMultimap<Object, Object> objectObjectArrayListMultimap = ArrayListMultimap.create();
        boolean e = objectObjectArrayListMultimap.put("e", 1);
        int count = objectObjectArrayListMultimap.keys().count(1);
        System.out.println("count = " + count);

        ConcurrentHashMultiset<Object> objects = ConcurrentHashMultiset.create();
        objects.iterator().forEachRemaining(i -> {
            System.out.println("i = " + i);
        });


        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        // {[1,10]}
        rangeSet.add(Range.closed(1, 10));
        //不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(11, 15));
        //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.closedOpen(15, 20));
        //空区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0));
        //分割[1, 10]; {[1,5], [10,10], [11,20)}
        rangeSet.remove(Range.open(5, 10));

        //*. OptionalObject.isPresent(): 返回对象是否有值。
        //*. Optional.absent(): 返回一个空Optional对象,isPresent() 将会返回false
        //*. Optional.of(): 创Optional对象，输入参数不能为null
        //*. Optional.fromNullable(): 创Optional对象，输入可以为null
        //*. OptionalObject.asSet(): 和Optional对象值合并，如果为null则返回size为0的Set
        //*. OptionalObject.or(): 和Optional对象值合并，如果为null为空加则返回or参数作为默认值
        //*. OptionalObject.orNull(): 和Optional对象值合并，如果为null为空加则返回Null作为默认值
        //上面的api都是我们在使用Optional的时候最常用的方法属性方法，注意如果我们创建了Optional对象，但是没有判断isPresent()是否存在，就直接get这是会抛异常的，这属于乱用Optional情况，和直接用Null并没什么差别。
//       当你需要用一个默认值来替换可能的null，请使用Objects.firstNonNull(T, T) 方法。如果两个值都是null，该方法会抛出NullPointerException。Optional也是一个比较好的替代方案，例如：Optional.of(first).or(second).
//       还有其它一些方法专门处理null或空字符串：emptyToNull(String)，nullToEmpty(String)，isNullOrEmpty(String)。我们想要强调的是，这些方法主要用来与混淆null/空的API进行交互。当每次你写下混淆null/空的代码时，Guava团队都泪流满面。（好的做法是积极地把null和空区分开，以表示不同的含义，在代码中把null和空同等对待是一种令人不安的坏味道。


//        Object o = com.google.common.base.Optional.absent().get();

        String sql = "1' or '1'='1";
        //防SQL注入
        System.out.println("防SQL注入:" + StringEscapeUtils.escapeSql(sql));
        //转义HTML,注意汉字
        System.out.println("转义HTML,注意汉字:" + StringEscapeUtils.escapeHtml("<font>chen磊  xing</font>"));
        //反转义HTML
        System.out.println("反转义HTML:" + StringEscapeUtils.unescapeHtml("<font>chen磊  xing</font>"));
        //转义成Unicode编码
        System.out.println("转成Unicode编码：" + StringEscapeUtils.escapeJava("陈磊兴"));
        //转义xml
        System.out.println("转义XML：" + StringEscapeUtils.escapeXml("<name>陈磊兴</name>"));
        //转义xml
        System.out.println("反转义XML：" + StringEscapeUtils.unescapeXml("<name>陈磊兴</name>"));


//        版权声明：本文为CSDN博主「Java我人生」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/chenleixing/article/details/43456987
        //guava 装饰器
        List<Integer> listint = Arrays.asList(1, 2, 3);
        HashSet<Integer> collect = listint.stream().collect(Collectors.toCollection(HashSet::new));

//        ForwardingList.get();
        boolean seen = false;
        Integer acc = null;
        for (Integer integer : listint) {
            if (!seen) {
                seen = true;
                acc = integer;
            } else {
//                acc = Integer.intValue(acc, integer);
            }
        }
        Optional<Integer> reduce = seen ? Optional.of(acc) : Optional.empty();

        Integer reduce1 = listint.stream().reduce(0, (a, b) -> {
            return a += b;
        });

        listint.stream().reduce(0, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return null;
            }
        }, new BinaryOperator<Integer>() {

            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return null;
            }

            @Override
            public <V> BiFunction<Integer, Integer, V> andThen(java.util.function.Function<? super Integer, ? extends V> after) {
                return null;
            }
        });

        // 求单词长度之和

        Stream<String> stream = Stream.of("I", "love", "you", "too");
        // 初始值　// / 累加器 //　// 部分和拼接器，并行执行时才会用到 // (3)
        Integer lengthSum = stream.reduce(0, (sum, str) -> sum + str.length(), (a, b) -> a + b);
        System.out.println("lengthSum = " + lengthSum);

// int lengthSum = stream.mapToInt(str -> str.length()).sum();

// Partition students into passing and failing
        //情况2：使用partitioningBy()生成的收集器，这种情况适用于将Stream中的元素依据某个二值逻辑（满足条件，或不满足）分成互补相交的两部分，比如男女性别、成绩及格与否等。下列代码展示将学生分成成绩及格或不及格的两部分。
        //
        //作者：Jimin
        //链接：http://www.imooc.com/article/24862
        //来源：慕课网
        //本文原创发布于慕课网 ，转载请注明出处，谢谢合作
//        Map<Boolean, List<Student>> passingFailing = students.stream()
//
//                .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
        //上只是分组的最基本用法，有些时候仅仅分组是不够的。在SQL中使用group by是为了协助其他查询，比如1. 先将员工按照部门分组，2. 然后统计每个部门员工的人数。Java类库设计者也考虑到了这种情况，增强版的groupingBy()能够满足这种需求。增强版的groupingBy()允许我们对元素分组之后再执行某种运算，比如求和、计数、平均值、类型转换等。这种先将元素分组的收集器叫做上游收集器，之后执行其他运算的收集器叫做下游收集器(downstream Collector)。
        //
        //// 使用下游收集器统计每个部门的人数
        //
        //Map<Department, Integer> totalByDept = employees.stream()
        //
        //                    .collect(Collectors.groupingBy(Employee::getDepartment,
        //
        //                                                   Collectors.counting()));// 下游收集器
        //
        //上面代码的逻辑是不是越看越像SQL？高度非结构化。还有更狠的，下游收集器还可以包含更下游的收集器，这绝不是为了炫技而增加的把戏，而是实际场景需要。考虑将员工按照部门分组的场景，如果我们想得到每个员工的名字（字符串），而不是一个个Employee对象，可通过如下方式做到：
        //
        //// 按照部门对员工分布组，并只保留员工的名字
        //
        //Map<Department, List<String>> byDept = employees.stream()
        //
        //                .collect(Collectors.groupingBy(Employee::getDepartment,
        //
        //                        Collectors.mapping(Employee::getName,// 下游收集器
        //
        //                                Collectors.toList())));// 更下游的收集器
        //
        //如果看到这里你还没有对Java函数式编程失去信心，恭喜你，你已经顺利成为Java函数式编程大师了。
        //
        //使用collect()做字符串join
        //
        //这个肯定是大家喜闻乐见的功能，字符串拼接时使用Collectors.joining()生成的收集器，从此告别for循环。Collectors.joining()方法有三种重写形式，分别对应三种不同的拼接方式。无需多言，代码过目难忘。
        //
        //// 使用Collectors.joining()拼接字符串
        //
        //Stream<String> stream = Stream.of("I", "love", "you");
        //
        ////String joined = stream.collect(Collectors.joining());// "Iloveyou"
        //
        ////String joined = stream.collect(Collectors.joining(","));// "I,love,you"
        //
        //String joined = stream.collect(Collectors.joining(",", "{", "}"));// "{I,love,you}"
        //
        //collect()还可以做更多
        //
        //除了可以使用Collectors工具类已经封装好的收集器，我们还可以自定义收集器，或者直接调用collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)方法，收集任何形式你想要的信息。不过Collectors工具类应该能满足我们的绝大部分需求，手动实现之间请先看看文档。
        //
        //
        //作者：Jimin
        //链接：http://www.imooc.com/article/24862
        //来源：慕课网
        //本文原创发布于慕课网 ，转载请注明出处，谢谢合作


        ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4)
                .reduce(new ArrayList<Integer>(),
                        new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {

                                acc.add(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("BiFunction");
                                return acc;
                            }
                        }, new BinaryOperator<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
                                System.out.println("BinaryOperator");
                                acc.addAll(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("--------");
                                return acc;
                            }
                        });
        System.out.println("accResult_: " + accResult_);
//// 结果打印
//        item: 1
//        acc+ : [1]
//        BiFunction
//        item: 2
//        acc+ : [1, 2]
//        BiFunction
//        item: 3
//        acc+ : [1, 2, 3]
//        BiFunction
//        item: 4
//        acc+ : [1, 2, 3, 4]
//        BiFunction
//        accResult_: [1, 2, 3, 4]
//        accResult_: 10


        Test test = new Test("", 1, 0) {
            @Override
            public void setI(int i) {
                super.setI(i);
            }

            @Override
            public void setS(String s) {
                super.setS(s);
            }

            @Override
            public void setIi(int ii) {
                super.setIi(ii);
            }
        };

        System.out.println("testo = " + test.getI());

        Test testo = new Test() {{
            this.setI(1);
            this.setS("ere");
            super.setI(444);
        }};
        System.out.println("testo = " + testo.getI());


    }


    public static Iterator<String> skipNulls(final Iterator<String> in) {
        return new AbstractIterator<String>() {
            @Override
            protected String computeNext() {
                while (in.hasNext()) {
                    String s = in.next();
                    if (s != null) {
                        return s;
                    }
                }
                return endOfData();
            }
        };
    }

    public class ListWithDefault<E> extends ForwardingList<E> {
        final E defaultValue;
        final List<E> delegate;

        ListWithDefault(List<E> delegate, E defaultValue) {
            this.delegate = delegate;
            this.defaultValue = defaultValue;
        }

        @Override
        protected List delegate() {
            return delegate;
        }

        @Override
        public E get(int index) {
            E v = super.get(index);
            return (v == null ? defaultValue : v);
        }

        @Override
        public Iterator<E> iterator() {
            final Iterator<E> iter = super.iterator();
            return new ForwardingIterator<E>() {
                @Override
                protected Iterator<E> delegate() {
                    return iter;
                }

                @Override
                public E next() {
                    E v = super.next();
                    return (v == null ? defaultValue : v);
                }
            };
        }
    }


    public int compareTo(Test t) {
        return ComparisonChain.start()
                .compare(this.s, t.s)
                .result();

    }

    public static <T> T CONVERT(T e) {
        if (e instanceof String) {
            e = (T) e;
        }
        if (e instanceof Integer) {
            e = (T) e;
        }
        if (e instanceof Byte) {
            e = (T) e;
        }

        if (e instanceof Double) {
            e = (T) e;
        }
        return null != e ? e : null;
    }

    public static <T> T[] toArray(T... items) {
        return items;
    }


}
