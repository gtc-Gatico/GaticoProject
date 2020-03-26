package cn.com.gatico.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/10/25 11:58
 */
public class aaaa_TwoNumSum {

    ///////////////////////////////////////////////////////////////////////////
    // 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    //
    //你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
    //
    //示例:
    //
    //给定 nums = [2, 7, 11, 15], target = 9
    //
    //因为 nums[0] + nums[1] = 2 + 7 = 9
    //所以返回 [0, 1]
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/two-sum
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        System.out.println(System.currentTimeMillis());
        int[] arr = twoSum(nums, target);
        System.out.println(System.currentTimeMillis());
        if (arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }
        System.out.println("结束");
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[2];
        /*int max = 2047;
        int[] res = new int[max + 1];

        for (int i = 0; i < nums.length; i++) {
            int ldiff = (target - nums[i]) & max;
            if (res[ldiff] != 0) {
                return new int[]{res[ldiff] - 1, i};
            }
            res[nums[i] & max] = i + 1;
        }

        return new int[2];*/
    }
}
