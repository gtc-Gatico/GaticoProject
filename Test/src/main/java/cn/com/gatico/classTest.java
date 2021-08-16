package cn.com.gatico;

public class classTest {
    public static void main(String[] args) throws Exception{
        ClassInnerClass classes = ClassInnerClass.class.newInstance();
        Class<?> enclosingClass = classes.getClass().getDeclaringClass();
        System.out.println(enclosingClass);
        ClassInnerClass.ClassInner c = new ClassInnerClass.ClassInner();
        System.out.println(c.getClass().getName());


    }
}

