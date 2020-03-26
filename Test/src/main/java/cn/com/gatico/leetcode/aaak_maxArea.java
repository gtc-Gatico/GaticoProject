package cn.com.gatico.leetcode;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/10/29 11:35
 */
public class aaak_maxArea {
    ///////////////////////////////////////////////////////////////////////////
    // 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    //
    //说明：你不能倾斜容器，且 n 的值至少为 2。
    //
    //
    //
    //图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
    //
    // 
    //
    //示例:
    //
    //输入: [1,8,6,2,5,4,8,3,7]
    //输出: 49
    //56 - 7
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/container-with-most-water
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(arr));
    }

    public static int maxArea(int[] height) {
        int max = 0;
        int maxVale = 0;
        int maxIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] * i > max) {
                max = height[i] * i;
                maxIndex = i;
            }
            if (height[i] > maxVale) {
                maxVale = height[i];
            }
            System.out.println(height[i] + "*" + i + "=" + height[i] * i);
        }
        System.out.println(maxVale);
        int mmax = 0;
        for (int i = 0; i < height.length && i != maxIndex; ) {
            System.out.println(Math.min(height[maxIndex], height[i]));
            if (max - (i + 1) * Math.min(height[maxIndex], height[i]) < mmax) {
                mmax = max - (i + 1) * Math.min(height[maxIndex], height[i]);
            }
            i++;
        }
        System.out.println(max + "," + maxIndex);

        return mmax;
    }
}
