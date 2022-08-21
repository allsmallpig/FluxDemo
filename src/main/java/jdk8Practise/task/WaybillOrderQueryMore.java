package jdk8Practise.task;

import lombok.Data;

import java.io.Serializable;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2019
 *
 * @author linzijing
 * @since Created in 2019/6/19 17:05
 */
@Data
public class WaybillOrderQueryMore implements Serializable {

    private static final long serialVersionUID = 1L;

    /** (value = "运单号") */
    private String waybillNo;


}
