package cn.com.gatico.leetcode;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/11/15 12:40
 */
public class moveZeroes {
    public static void main(String[] args) {
        int[] nums = moveZeroes(new int[]{0, 1});
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static int[] moveZeroes(int[] nums) {
        int i = nums.length - 1;
        while (i > -1) {
            if (nums[i] == 0) {
                for (int j = i; j < nums.length - 1; j++) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
            i--;
        }
        return nums;
    }

}
