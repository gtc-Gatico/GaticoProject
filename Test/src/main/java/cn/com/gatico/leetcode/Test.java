package cn.com.gatico.leetcode;

import org.apache.poi.util.StringUtil;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int a[] = new int[]{1, 3, 5, 6};
        // System.out.println(searchInsert(a, 2));
        // int i = Double.valueOf("-2147483648").intValue();
        //System.out.println(countDigitOne(824883294));
//        long b = System.currentTimeMillis();
//        String tmp ="For a computer, having a lot of Pi is like a human having a lot of pie: It makes it fat and slow. If you store Pi in a normal, human-readable text file (like you can create in Notepad, Emacs, vi, or whatever your favorite editor is), then Pi requires one byte per digit. So 200,000,000 digits of pi takes up 200,000,000 bytes, or 200 megabytes. Even with fast computers, it can still take a few seconds to read 200 megabytes of data from a hard disk, and a significant fraction of a second even to read through it when it's already in memory!\n" +
//                "\n" +
//                "Version 2 of the Pi searcher used only half this space. Observe that Pi in decimal consists of only 10 characters (0, 1, 2, 3, 4, 5, 6, 7, 8, 9), unlike general files that can contain 256 different ASCII characters (0-9, a-z, A-Z, and all of the punctuation characters, and then a big set of \"unprintable\" characters). Computers store information as bits - ones and zeros. Normally, one character is stored as a byte, or 8 bits. (28 = 256, the number of different characters). (Using the more modern Unicode representation, they can be even larger.) But we only have 10 possibilities, so the Pi searcher can store those using 4 bits each (24 = 16, just a little more than we need).\n" +
//                "\n" +
//                "So, the solution: The pi searcher stores the digits of Pi packed two digits per byte. As a result, the file of Pi is totally unreadable to a human, but it's half the size of the human-readable version!\n" +
//                "\n" +
//                "If you were counting carefully, you'll have noticed that we use 4 bits (which can hold 16 values) to store 10 different values. So there's actually more room for savings: We technically need only log2 (10) = 3.322 bits to store each number. Unfortunately, saving that extra .6 bits per digit makes the code a lot more complicated, and slower.";
//        String p = "characters";
//        System.out.println(tmp.indexOf(p));
//        System.out.println(System.currentTimeMillis() - b);
//        b = System.currentTimeMillis();
//        System.out.println(strStr(tmp, p));
//        System.out.println(System.currentTimeMillis() - b);

//        System.out.println(countDigitOne(824883294));

//        int arr[] = {1, 1, 2};
//        System.out.println(removeDuplicates(arr));

//        String s = "aaa";
//        System.out.println(largeGroupPositions(s));
        String s1 = "6994";
        String s2 = "36";
        String s3 = addStrings(s1, s2);
        System.out.println(s3);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int arr[] = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, arr, 0, nums1.length);
        System.arraycopy(nums2, 0, arr, nums1.length, nums2.length);
        Arrays.sort(arr);
        if (arr.length % 2 == 0) {
            return ((double) arr[Integer.valueOf((arr.length - 1) / 2)] + (double) arr[Integer.valueOf((arr.length) / 2)]) / 2;
        } else {
            return arr[Integer.valueOf((arr.length - 1) / 2)];
        }
    }

    public static int jump(int[] nums) {
        int length = nums.length;

        return 0;
    }

    public static int firstMissingPositive(int[] nums) {
        Set set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (i != 0 && !set.contains(i)) {
                return i;
            }
        }
        return set.size();
    }

    public static int reverseBits(int n) {
        String nums = Integer.toBinaryString(n);
        System.out.println(nums);//00000010100101000001111010011100
        List<String> characterList = Arrays.asList(nums.split(""));
        for (int i = 0; i < 32 - characterList.size() - 1; i++) {
            characterList.add("0");
        }
        Collections.reverse(characterList);
        String tmp = StringUtil.join(characterList.toArray());
        System.out.println(tmp);//00111001011110000010100101000000
        return Integer.parseInt(tmp, 2);
    }

    public static String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }

    public static int repeatedNTimes(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            int v = map.get(A[i]) == null ? 0 : map.get(A[i]);
            map.put(A[i], ++v);
            System.out.println(map.toString());
            if (map.get(A[i]) >= A.length / 2) {
                return A[i];
            }
        }
        return 0;
    }

    //1 2 4 5 , 3
    public static int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        if (target < nums[0]) {
            return 0;
        }
        int left = 0;//0
        int right = nums.length - 1;//3
        int mid = 0;//2
        while (left <= right) {
            mid = (right + left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return -1;
    }

    public static int hammingWeight(int n) {
        int c = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                c++;
            }
            n = n >> 1;
        }
        return c;
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }

    public static double log2(double N) {
        return Math.log(N) / Math.log(2);//Math.log的底为e
    }

    public static int countDigitOne(int n) {
        //12345678910111213
        int c = 0;
        for (int i = 1; i <= n; i++) {
            char[] chars = String.valueOf(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '1') {
                    c++;
                }
            }
        }
        return c;
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        char[] toCharArray = haystack.toCharArray();
        char[] chars = needle.toCharArray();
        boolean flag = false;
        for (int i = 0; i < toCharArray.length; i++) {
            if (toCharArray[i] == chars[0]) {
                for (int j = 0; j < chars.length; j++) {
                    if (i + j > toCharArray.length - 1 || toCharArray[i + j] != chars[j]) {
                        flag = false;
                        break;
                    }
                    flag = true;
                }
                if (flag) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int titleToNumber(String s) {
        //A-Z=65-90
        char[] split = s.toCharArray();
        int index = 0;
        for (int i = 0, j = split.length - 1; i < split.length; i++, j--) {
            index += i == 0 ? ((int) split[j] - 65 + 1) : Math.pow(26, i) * ((int) split[j] - 65 + 1);
        }
        return index;
    }

    public static int removeDuplicates(int[] nums) {
        List<Integer> arr = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!arr.contains(nums[i])) {
                arr.add(nums[i]);
            }
        }
        int arr2[] = new int[arr.size()];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr.get(i);
        }
        System.arraycopy(arr2, 0, nums, 0, arr.size());
        return arr.size();
    }

    //abbxxxxzyy
    public static List<List<Integer>> largeGroupPositions(String s) {
        s += " ";
        List<List<Integer>> listList = new ArrayList<>();
        char tmp = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == tmp) {
                count++;
            } else {
                tmp = s.charAt(i);
                if (count >= 3) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(i - count);
                    integers.add(i - 1);
                    listList.add(integers);
                }
                count = 1;
            }
        }
//        if (count >= 3) {
//            ArrayList<Integer> integers = new ArrayList<>();
//            integers.add(i - count);
//            integers.add(i - 1);
//            listList.add(integers);
//        }
        return listList;
    }

    public static String addStrings(String num1, String num2) {
        String n1[] = num1.split("");
        String n2[] = num2.split("");
        int i1 = n1.length - 1;
        int i2 = n2.length - 1;
        if (i1 < i2) {
            String[] c1 = n1;
            n1 = n2;
            n2 = c1;
            i1 = n1.length - 1;
            i2 = n2.length - 1;
        }
        boolean flag = false;
        while (i1 >= 0 && i2 >= 0) {
            Integer val = Integer.valueOf(n1[i1]) + Integer.valueOf(n2[i2]);
            if (val >= 10) {
                n1[i1] = Integer.valueOf(val - 10).toString();
                if (i1 - 1 < 0) {
                    flag = true;
                } else {
                    Integer r1 = Integer.valueOf(Integer.valueOf(n1[i1 - 1]) + 1);
                    n1[i1 - 1] = r1.toString();

                    int i3 = i1 - 1;
                    while (r1 >= 10) {
                        n1[i3] = Integer.valueOf(r1 - 10).toString();
                        Integer r2 = r1 - 10 + 1;
                        n1[i3 - 1] = Integer.valueOf(Integer.valueOf(n1[i3 - 1]) + r2).toString();
                        r1 = Integer.valueOf(n1[i3 - 1]);
                        i3--;
                    }
                }
            } else {
                n1[i1] = val.toString();
            }
            i1--;
            i2--;
        }
        String res = String.join("", n1);
        if (flag) {
            res = "1" + res;
        }
        return res;
    }
}