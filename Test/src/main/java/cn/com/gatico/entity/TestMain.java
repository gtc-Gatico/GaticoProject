package cn.com.gatico.entity;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/11/19 10:30
 */
public class TestMain {
    public static void main(String[] args) {
        int[] arr = new int[102400000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
            //System.out.print(arr[i] + " ");
            /*if (i % 1000 == 0) {
                System.out.println();
            }*/
        }
        System.out.println("\n------------");
        int[] arr2 = new int[arr.length];
        long t1 = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr2, 0, arr2.length);
        System.out.println(System.currentTimeMillis() - t1);
        System.out.println("------------");
        /*for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]+ " ");
           *//* if (i % 1000 == 0) {
                System.out.println();
            }*//*
        }*/
    }

    public static int search(String txt, String pat) {
        int N = txt.length();
        int M = pat.length();
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) break;
            }
            if (j == M) return i;
        }
        return N;
    }
}
