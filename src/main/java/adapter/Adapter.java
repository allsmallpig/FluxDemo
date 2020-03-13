package adapter;

/**
 * @author
 * @packageName atm
 * @date 2020/1/19 15:32
 * @Description <a href="goodmanalibaba@foxmail.com"></a>
 * @Versin 1.0
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void method2() {
        System.out.println("method 2");
    }
}

//// 测试
//class AdapterTest {
//    public static void main(String[] args) {
//        Adapter adapter = new Adapter();
//        adapter.method1();
//        adapter.method2();
//    }
//}
