package annoation_os;

/**
 * @author litiezhu / 804082784@qq.com
 * @version 1.0
 * @date 2019/10/18-19:29
 */
public class Student {

    @TestAnnoation
    private Integer i;
    private Integer c;

    @TestAnnoation
    public void test(Integer i, Integer c) {
        this.i = i;
        this.c = c;
        System.out.println("i = " + i + ", c =" + c);
    }

    public static void main(String[] args) {
        new Student().test(0, null);
    }
}
