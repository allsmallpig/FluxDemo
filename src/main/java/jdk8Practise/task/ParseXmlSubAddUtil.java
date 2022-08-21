package jdk8Practise.task;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * 云路供应链科技有限公司 版权所有  Copyright 2021
 *
 * @author stein | litiezhu | 804082784@qq.com
 * @date 2022-07-17 17:37
 * @description
 */
public class ParseXmlSubAddUtil {
    public static void main(String[] args) throws Exception {
//        Double dOriginal = 237D;
//        Double now = 273D;
        Double result =  25D ;

        FileInputStream fin = new FileInputStream("D:\\nohead\\yl-wtjmy-manage-api\\WebRoot\\jasper\\06_27_1_PP_CASH.jrxml");
        LinkedList<String> strings = new LinkedList(IOUtils.readLines(fin, "UTF-8"));
        LinkedList<String> writeList = Lists.newLinkedList();
        for (int z = 0; z < strings.size(); z++) {
            String i = strings.get(z);
            String initStr = "height=";
            int length = initStr.length();
            if (i.contains(initStr)) {
                int begininedx = i.indexOf(initStr);
                String num = "";
                String substring36 = i.substring(begininedx + length + 1, begininedx + length + 4);
                String substring35 = i.substring(begininedx + length + 1, begininedx + length + 3);
                String substring34 = i.substring(begininedx + length + 1, begininedx + length + 2);
                if (StringUtils.isNotBlank(substring36) && StringUtils.isNumeric(substring36)) {
                    BigDecimal bigDecimal = new BigDecimal(Double.valueOf(substring36) + result).setScale(0, BigDecimal.ROUND_HALF_UP);
                    double vd = bigDecimal.doubleValue();
                    String s = String.valueOf(vd);
                    System.out.println("substring36 = " + substring36 + "===num4 =============== " + s.substring(0, s.lastIndexOf(".")));
                    i = i.substring(0, begininedx + length) + "\"" + s.substring(0, s.lastIndexOf(".")) + i.substring(begininedx + length + 4, i.length());
                    writeList.add(i);
                    continue;
                } else if (StringUtils.isNotBlank(substring35) && StringUtils.isNumeric(substring35)) {
                    if (StringUtils.isNotBlank(substring35)) {
                        BigDecimal bigDecimal = new BigDecimal(Double.valueOf(substring35) + result).setScale(0, BigDecimal.ROUND_HALF_UP);
                        double vd = bigDecimal.doubleValue();
                        String s = String.valueOf(vd);
                        System.out.println("num3 = " + substring35 + "===substring35=========" + s.substring(0, s.lastIndexOf(".")));
                        i = i.substring(0, begininedx + length) + "\"" + s.substring(0, s.lastIndexOf("."))/* + "\""*/ + i.substring(begininedx + length + 3, i.length());
//                                System.out.println("num3 ================ " + substring35);
                        writeList.add(i);
                        continue;
                    }
                } else if (StringUtils.isNotBlank(substring34) && StringUtils.isNumeric(substring34)) {
                    BigDecimal bigDecimal = new BigDecimal(Double.valueOf(substring34) + result).setScale(0, BigDecimal.ROUND_HALF_UP);
                    double vd = bigDecimal.doubleValue();
                    String s = String.valueOf(vd);
                    System.out.println("num2 = " + substring34 + "===substring35=========" + s.substring(0, s.lastIndexOf(".")));
                    i = i.substring(0, begininedx + length) + "\"" + s.substring(0, s.lastIndexOf(".")) /*+ "\"" */ + i.substring(begininedx + length + 2, i.length());
                    writeList.add(i);
                    continue;
                }
            }
            writeList.add(i);
        }


        System.out.println("writeList = " + JSON.toJSONString(writeList));

    }
}


