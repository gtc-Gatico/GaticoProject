package cn.com.gatico.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/10/26 22:04
 */
public class aaad_findMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            list.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            list.add(nums2[i]);
        }
        Collections.sort(list);
        if (list.size() % 2 == 0) {
            double sum = (list.get(list.size() / 2 - 1) + list.get((list.size()) / 2));
            return sum / 2;
        } else {
            return list.get(list.size() / 2);
        }
    }
}
