import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2021/11/2 11:15
 * @description
 */
@Slf4j
@Data
public class sss {
    LocalDateTime startTime;
    LocalDateTime endTime;
    public static void main(String[] args) throws IOException {

        try {
            int i  = 10/0;
        } catch (Exception e) {
            log.error("---测试异常信息e:---", e);
            log.info("---分割线分割线分割线分割线分割线分割线分割线分割线:---");
            log.error("---测试异常信息e:{}---", e);
            log.info("---分割线分割线分割线分割线分割线分割线分割线分割线:---");
            log.error("----测试异常信息 getFullStackTrace --- e:{}----", org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            log.info("---分割线分割线分割线分割线分割线分割线分割线分割线:---");
            log.error("----测试异常信息 getMessage ---e:{}----", ExceptionUtils.getMessage(e));
            log.info("---分割线分割线分割线分割线分割线分割线分割线分割线:---");
            log.error("---测试异常信息 getCause e:{}---", ExceptionUtils.getCause(e));
            log.info("---分割线分割线分割线分割线分割线分割线分割线分割线:---");

        }


    }
}
