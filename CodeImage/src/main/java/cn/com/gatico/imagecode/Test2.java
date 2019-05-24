package cn.com.gatico.imagecode;

public class Test2 {
    public static void main(String[] args) {
        int arr[] = new int[]{5, 2, 4, 6, 1, 3};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
        int key = 0;
        //1  5   2 5 4 6 1 3
        //2  5   2 4 5 6 1 3
        //3  5
        for (int j = 1; j < arr.length; j++) {
            key = arr[j-1];
            if(key>arr[j]){
                arr[j]=key;
                arr[j-1]=arr[j];

            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
}
