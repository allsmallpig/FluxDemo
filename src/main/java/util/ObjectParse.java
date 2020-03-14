package util;


/**
 * @author litiezhu / 804082784@qq.com
 * @version 1.0
 * @date 2019/10/14-19:54
 */
public class  ObjectParse<Student extends Object> {

    public void convert(Class<?> s) {
        try {
            Object t = s.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public <E> E ObjectParse(E e) {
        E b = e instanceof Object ? (e) : null;
        if (b == null) {
            throw new NullPointerException();
        }
        return b;
    }

}
