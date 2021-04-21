package algorithm.leetcode;


import com.alibaba.fastjson.JSON;

/**
 * 两数之和
 * doc:https://leetcode-cn.com/problems/two-sum/
 */
public class Solution {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5};

        int[] ints1 = twoSum(ints, 4);
        System.out.println("ints1 = " + JSON.toJSONString(ints1));

    }

    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    //另外一种方法
/*    public static  int[] twoSum(int[] nums, int target) {

        List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int firstCheck = target - num;
            boolean contains = collect.contains(firstCheck);
            if (contains) {
                int firstCheckIndex = collect.indexOf(firstCheck);
                if (i != firstCheckIndex) {
                    int[] ints = {i, firstCheckIndex};
                    return ints;
                }
            }
        }
        return null;
    }*/


}
