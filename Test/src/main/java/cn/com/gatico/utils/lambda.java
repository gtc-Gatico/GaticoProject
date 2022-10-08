package cn.com.gatico.utils;

public class lambda implements MyAInterface {
    static  lambda lambda = new lambda();
    public static void main(String[] args) {

        System.out.println(lambda.sum(1, 2));
    }

    @Override
    public int sum(int a, int b) {
        return 2*a+2*b;
    }
}

interface MyAInterface {
   default int sum(int a, int b){
       return a + b;
   }
}

//interface MyBInterface {
//    default int sum(int a, int b) {
//        return a + b;
//    }
//
//}