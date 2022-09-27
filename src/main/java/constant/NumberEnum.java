package constant;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2019
 * 数字枚举
 *
 * @author wanghuizhen
 * @since Created in 2020-01-03
 */
public enum NumberEnum {
    NEGATIVE_ONE(-1),
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    FIFTEEN(15),
    THIRTY(30),
    THIRTY_ONE(31),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    THOUSAND(1000),
    TWO_THOUSAND(2000),
    ;
    private Integer code;

    NumberEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
