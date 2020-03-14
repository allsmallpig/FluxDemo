package jdk8Practise.functionalInterface;

import java.util.function.Function;

/**
 * @author
 * @packageName main
 * @date 2020/2/5 16:56
 * @Description <a href="goodmanalibaba@foxmail.com"></a>
 * @Versin 1.0
 */

public class Test2FunctionalInterface {
    public String name = "22";

    public Test2FunctionalInterface(String name) {
        this.name = name;
    }

    public Test2FunctionalInterface setName(String name) {
        this.name = name;
        return this;
    }

    public Test2FunctionalInterface() {
    }

    public String getName() {
        return name;
    }

    public static void testExcute(DoInterface t, String s) {
        t.excute(s);
    }

    public static void testdoAction(Test2FunctionalInterface.Funtace innnerFace, String s) {
        innnerFace.doAction(s);
    }

    @FunctionalInterface
    interface Funtace {
        abstract void doAction(String s);
    }


    public static void main(String[] args) throws Throwable {
//        DoInterface op = f -> System.out.println("s = " + f);
//        op.excute("t");


//        DoInterface op = (f,t) -> System.out.println("s = " + f + t);
//        op.excuteStr("hello"," world");

        //--------------------------------
        /** 传统写法*/
        testExcute(new DoInterface() {
            @Override
            public void excute(String var) {
                System.out.println("var = " + var);
            }
        }, "6");
        /** 是上面的简化写法*/
        testExcute(var -> System.out.println("var = " + var), "6");
        //--------------------------------

        String s = "3";
        testExcute((d) -> System.out.println("d = " + d), "rr");

        testdoAction((z) -> {
            System.out.println("action start =" + z);
        }, "22");

//        DoInterface op = f -> {throw f;};
//        op.except(new Exception());


        Function<String, String> lambdaFunction = (String var) -> var.toLowerCase();
        System.out.println(lambdaFunction.apply("JAVA"));
    }
}
