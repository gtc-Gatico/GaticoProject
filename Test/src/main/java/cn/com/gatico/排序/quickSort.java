package cn.com.gatico.排序;

import java.util.Random;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/12/9 12:01
 */
public class quickSort {
    //快速排序
    public static int[] qsort(int arr[], int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while ((i < j) && (arr[j] > pivot)) {
                j--;
            }
            while ((i < j) && (arr[i] < pivot)) {
                i++;
            }
            if ((arr[i] == arr[j]) && (i < j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i - 1 > start) arr = qsort(arr, start, i - 1);
        if (j + 1 < end) arr = qsort(arr, j + 1, end);
        return (arr);
    }

    //选择排序
    public static void selectionSort(int arr[]) {
        int len = arr.length;
        int minIndex, temp;
        for (int i = 0; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {     // 寻找最小的数
                    minIndex = j;                 // 将最小数的索引保存
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    //插入排序
    public static void insertionSort(int arr[]) {
        int len = arr.length;
        int preIndex, current;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    //希尔排序
    public static void shellSort(int arr[]) {
        int len = arr.length;
        for (int gap = (int) Math.floor(len / 2); gap > 0; gap = (int) Math.floor(gap / 2)) {
            // 注意：这里和动图演示的不一样，动图是分组执行，实际操作是多个分组交替执行
            for (int i = gap; i < len; i++) {
                int j = i;
                int current = arr[i];
                while (j - gap >= 0 && current < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j = j - gap;
                }
                arr[j] = current;
            }
        }
    }

    //归并排序
    public static int[] mergeSort(int[] nums, int l, int h) {
        if (l == h)
            return new int[]{nums[l]};

        int mid = l + (h - l) / 2;
        int[] leftArr = mergeSort(nums, l, mid); //左有序数组
        int[] rightArr = mergeSort(nums, mid + 1, h); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];
        return newNum;
    }

    //堆排序
    public static void heapSort(int[] array) {
        //这里元素的索引是从0开始的,所以最后一个非叶子结点array.length/2 - 1
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);  //调整堆
        }

        // 上述逻辑，建堆结束
        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            // 元素交换,作用是去掉大顶堆
            // 把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(array, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(array, 0, j);
        }
        //return array;
    }

    /**
     * 整个堆排序最关键的地方
     *
     * @param array  待组堆
     * @param i      起始结点
     * @param length 堆的长度
     */
    public static void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {  //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i = k;
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a   元素的下标
     * @param b   元素的下标
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //计数排序
    public static void countSort(int[] a) {
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for (int i : a) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }//这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int c[] = new int[k];
        for (int i = 0; i < a.length; ++i) {
            c[a[i] - min] += 1;//优化过的地方，减小了数组c的大小
        }
        for (int i = 1; i < c.length; ++i) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = a.length - 1; i >= 0; --i) {
            b[--c[a[i] - min]] = a[i];//按存取的方式取出c的元素
        }
        //return b;
    }

    //基数排序
    public static void radixSort(int[] number, int d) //d表示最大的数有多少位
    {
        int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        int[][] temp = new int[10][number.length]; //数组的第一维表示可能的余数0-9
        int[] order = new int[10]; //数组orderp[i]用来表示该位是i的数的个数
        while (m <= d) {
            for (int i = 0; i < number.length; i++) {
                int lsd = ((number[i] / n) % 10);
                temp[lsd][order[lsd]] = number[i];
                order[lsd]++;
            }
            for (int i = 0; i < 10; i++) {
                if (order[i] != 0)
                    for (int j = 0; j < order[i]; j++) {
                        number[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }

    public static void main(String[] args) throws Exception {
        int size = 10;
        int arr1[] = new int[size];
        int arr2[] = new int[size];
        int arr3[] = new int[size];
        int arr4[] = new int[size];
        int arr5[] = new int[size];
        int arr6[] = new int[size];
        int arr7[] = new int[size];
        int arr8[] = new int[size];
        int arr9[] = new int[size];
        int arr10[] = new int[size];
        Random random = new Random();
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt(size * size);
        }
      /*  System.arraycopy(arr1, 0, arr2, 0, arr1.length);
        System.arraycopy(arr1, 0, arr3, 0, arr1.length);
        System.arraycopy(arr1, 0, arr4, 0, arr1.length);
        System.arraycopy(arr1, 0, arr5, 0, arr1.length);
        System.arraycopy(arr1, 0, arr6, 0, arr1.length);
        System.arraycopy(arr1, 0, arr7, 0, arr1.length);
        System.arraycopy(arr1, 0, arr8, 0, arr1.length);
        System.arraycopy(arr1, 0, arr9, 0, arr1.length);*/
        System.arraycopy(arr1, 0, arr10, 0, arr1.length);
        arr10 = new int[]{1, 9, 3, 4, 6, 2, 5, 7, 8, 0};
        //maopaoTest(arr1);//冒泡
        maopao2Test(arr10);//冒泡2
        /*sortTest(arr2);//双向选择
        qsTest(arr3);//快速
        selectionSortTest(arr4);//选择
        insertionSortTest(arr5);//插入
        shellSortTest(arr6);//希尔
        mergeSortTest(arr7);//归并
        heapSortTest(arr8);//堆排序
        countSortTest(arr9);//计数*/

    }

    public static void sort(int arr[]) {
        int start = 0;
        int length = arr.length;
        while (start < arr.length / 2) {
            int min = arr[start], mini = start;
            int max = arr[start], maxi = start;
            for (int j = start; j < length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    mini = j;
                }
                if (max < arr[j]) {
                    max = arr[j];
                    maxi = j;
                }
            }
            if (arr[start] != min) {
                arr[mini] = arr[start];
                arr[start] = min;
                maxi++;
            }
            if (arr[length - 1] != max) {
                arr[maxi] = arr[length - 1];
                arr[length - 1] = max;
            }
            start++;
            length--;
        }
    }

    public static void maopao(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void maopao2(int arr[]) {
        int max = arr.length - 1;
        int min = 0;
        int index = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] > arr[max]) {
                swap(arr, j, max);
                j = 0;
            }
            if (arr[j] == arr[max]) {
                swap(arr, j, max - 1);
                j = 0;

            }
            if (arr[j] < arr[min]) {
                swap(arr, j, min);
                j = 0;
            }
                /*if (arr[j] == arr[min]) {
                    swap(arr, j, min + 1);
                }*/
            printArray(arr);
        }
    }

    public static void printArray(int[] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ",");
            }
        }
        System.out.println();
    }

    public static void qsTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        int len = arr.length - 1;
        arr = qsort(arr, 0, len);
        long e = System.nanoTime();
        System.out.print("快速排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        int len2 = arr.length - 1;
        arr = qsort(arr, 0, len2);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void maopaoTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        maopao(arr);
        long e = System.nanoTime();
        System.out.print("冒泡排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        maopao(arr);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void maopao2Test(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        printArray(arr);
        maopao2(arr);
        long e = System.nanoTime();
        System.out.print("冒泡2排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        printArray(arr);
        maopao2(arr);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void sortTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        sort(arr);
        long e = System.nanoTime();
        System.out.print("双向选择排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        sort(arr);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void selectionSortTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        selectionSort(arr);
        long e = System.nanoTime();
        System.out.print("选择排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        selectionSort(arr);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void insertionSortTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        insertionSort(arr);
        long e = System.nanoTime();
        System.out.print("插入排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        insertionSort(arr);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void shellSortTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        shellSort(arr);
        long e = System.nanoTime();
        System.out.print("希尔排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        shellSort(arr);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void mergeSortTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        long e = System.nanoTime();
        System.out.print("归并排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void heapSortTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        heapSort(arr);
        long e = System.nanoTime();
        System.out.print("堆排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        heapSort(arr);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void countSortTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        countSort(arr);
        long e = System.nanoTime();
        System.out.print("计数排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        countSort(arr);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }

    public static void radixSortTest(int arr[]) throws Exception {
        Thread.sleep(1000);
        long b = System.nanoTime();
        radixSort(arr, 3);
        long e = System.nanoTime();
        System.out.print("基数排序(" + arr.length + ")\t无序用时\t" + (e - b));

        Thread.sleep(1000);
        b = System.nanoTime();
        radixSort(arr, 3);
        e = System.nanoTime();
        System.out.println("\t有序用时\t" + (e - b));
    }
}
