package annoation_os;

import java.lang.annotation.*;

/**
 * @author litiezhu / 804082784@qq.com
 * @version 1.0
 * @date 2019/10/18-19:23
 */
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnoation {

    public int value() default 1;

}
