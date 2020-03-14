package leetcode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author
 * @packageName main
 * @date 2020/2/4 22:35
 * @Description <a href="goodmanalibaba@foxmail.com"></a>
 * @Versin 1.0
 */
public class Test {

    public static void main(String[] args) {
        Character dxfdbf = Result.mostFrequentLetter("dxfdbfERRFRR3245{}+}+}+");
        System.out.println("dxfdbf = " + dxfdbf);
        System.out.println("d = " + (int) new Character("d".toCharArray()[0]));
        System.out.println("f = " + (int) new Character("f".toCharArray()[0]));
        System.out.println("f = " + "F".toLowerCase());
//
////        boolean wer33 = Test.judgeContainsStr("33");
////        System.out.println("wer33 = " + wer33);
////        String s = "wang...test4.jpg".replaceAll("[^\\u4e00-\\u9fa5a-zA-Z]", "");
////        System.out.println(s);
    }

//
//    /**
//     * 该方法主要使用正则表达式来判断字符串中是否包含字母
//     * @author fenggaopan 2015年7月21日 上午9:49:40
//     * @param cardNum 待检验的原始卡号
//     * @return 返回是否包含
//     */
//    public static boolean judgeContainsStr(String cardNum) {
//        String regex = ".*[a-zA-Z]+.*";
//        Matcher m = Pattern.compile(regex).matcher(cardNum);
//        return m.matches();
//
//    }

    static class Result {

        public static boolean judgeContainsStr(String cardNum) {
            String regex = ".*[a-zA-Z]+.*";
            Matcher m = Pattern.compile(regex).matcher(cardNum);
            return m.matches();
        }

        /**
         * 找出字符串中出现次数最多的字母，次数相同的情况下返回ascii code更小的，没有字母的话返回null(忽略大小写，统一当成小写处理).
         *
         * <p>
         * 提示:
         * 1. 字符串长度在1000以内，且只包含ascii字符
         * </p>
         *
         * @param string 只包含ascii字符的字符串.
         * @return 出现次数最多的字母.
         */

        public static Character mostFrequentLetter(String string) {

            //没有字母
            if (Objects.isNull(string) || !judgeContainsStr(string)) {
                return null;
            }
            string = string.replaceAll("[^\\u4e00-\\u9fa5a-zA-Z]", "").toLowerCase();
            char[] chars = string.toCharArray();
            TreeMap<Character, Integer> integerHashMap = new TreeMap<Character, Integer>();
            ArrayList<Character> objects = new ArrayList<>();
            for (char aChar : chars) {
                objects.add(aChar);
            }
            objects.stream().forEachOrdered(i -> {
                Integer coutNum = integerHashMap.get(i);
                if (Objects.nonNull(coutNum)) {
                    integerHashMap.put(i, coutNum + 1);
                } else {
                    integerHashMap.put(i, 1);
                }
            });

            List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(integerHashMap.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
                //升序排序
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            if (null != integerHashMap && integerHashMap.size() > 0) {
//                Set<Map.Entry<Character, Integer>> entries = integerHashMap.entrySet();
                Iterator<Map.Entry<Character, Integer>> iterator = list.iterator();
                Map.Entry<Character, Integer> nextFirst = null;
                Map.Entry<Character, Integer> nextSecond = null;
                int i = 0;
                while (iterator.hasNext() == true) {
                    i++;
                    Map.Entry<Character, Integer> next = iterator.next();
                    if (i == 1) {
                        nextFirst = next;
                    }
                    if (i == 2) {
                        nextSecond = next;
                    }
                }
                if (nextFirst.getValue().equals(nextSecond.getValue())) {
                    Character firstKey = nextFirst.getKey();
                    Character secondkey = nextSecond.getKey();
                    if ((int) firstKey > (int) secondkey) {
                        return secondkey;
                    } else if ((int) firstKey < (int) secondkey) {
                        return firstKey;
                    }
                } else {
                    //不相等时候的判断
                    Character firstKey = nextFirst.getKey();
                    return firstKey;
                }
            }
            return null;
        }

    }
}
