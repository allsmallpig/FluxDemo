package AppWare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Test<E> {


    public static void main(String[] args) {
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        for (int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i));
        }

        /* 放弃使用显式的for循环，因其与生俱来的顺序执行的特性会阻碍并行化。换言之，使用内部遍历时，
        程序的并行程度能够很容易地被提高。
        声明式的代码比命令式的代码更简洁，更具有可读性，更优雅。
         列表的变换*/
        friends.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = " + s);
            }
        });
        /*高阶函数，因为它接受了一个Lambda表达式作为其参数 */
        friends.forEach((final String s) -> System.out.println("s = " + s));
        /*类型推导*/
        friends.forEach((s) -> System.out.println("s = " + s));
        /*方法引用*/
        friends.forEach(System.out::println);

        final List<String> uppercaseNames = new ArrayList<String>();
        for (String name : friends) {
            uppercaseNames.add(name.toUpperCase());
        }

        final List<String> uppercaseNamesNew = new ArrayList<String>();
        friends.forEach(name -> uppercaseNamesNew.add(name.toUpperCase()));
        System.out.println(uppercaseNames);

        friends.stream()
                .map(name -> name.toUpperCase())
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        friends.stream()
                .map(name -> name.length())
                .forEach(count -> System.out.print(count + " "));


    }

}
