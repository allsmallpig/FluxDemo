package jdk8Practise.task;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2019
 *
 * @author linzijing
 * @since Created in 2019/6/19 17:05
 */
@Data
public class WaybillVOQueryMore implements Serializable {

    private static final long serialVersionUID = 1L;

    /** (value = "运单号") */
    private String waybillNo;

    /** (value = "1 一段码 2 二段码") */
    private Integer segmentCodeType;

    /** (value = "1 一段码 2 二段码") */
    private String segmentCode;
}
