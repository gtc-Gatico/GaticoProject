package cn.com.gatico.TestSet;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        List a = Arrays.asList(1, 2, 3, 4, 5);
        List b = Arrays.asList(1, 4, 6, 7, 8);

        System.out.println(intersection(a, b));//交 [1, 4]
        System.out.println(unionSet(a, b));//并 [1, 2, 3, 4, 5, 6, 7, 8]
        System.out.println(complementarySet(a, b));//补 [6, 7, 8]
        System.out.println(complementarySet(b, a));//补 [2, 3, 5]
        //取交集
        Set removeSet = new HashSet(a);
        removeSet.retainAll(b);
        System.out.println(removeSet);//[1, 4]

        //删除相同的部分
        Set resA = new HashSet<>(a);
        resA.removeAll(removeSet);
        System.out.println(resA);//[2, 3, 5]

        //删除相同的部分
        Set resB = new HashSet<>(b);
        resB.removeAll(removeSet);
        System.out.println(resB);//[6, 7, 8]


        //数据库
        Set<Long> entityIdSet = new HashSet();
        entityIdSet.add(2L);
        entityIdSet.add(4L);
        entityIdSet.add(5L);
        entityIdSet.add(6L);
        //实际
        Set<Long> boxIdsList = new HashSet();
        boxIdsList.add(2L);
//        boxIdsList.add(3L);
//        boxIdsList.add(4L);
        boxIdsList.add(7L);
//        boxIdsList.add(8L);
//        boxIdsList.add(9L);
        Set<Long> needAddSet = new HashSet();
        Set<Long> needDelSet = new HashSet();
        Set<Long> needUdpSet = new HashSet();

        needAddSet.addAll(boxIdsList);
        needAddSet.removeAll(entityIdSet);

        needDelSet.addAll(entityIdSet);
        needDelSet.removeAll(boxIdsList);

        needUdpSet.addAll(boxIdsList);
        needUdpSet.retainAll(entityIdSet);

        System.out.println("--------添加----------");
        needAddSet.forEach(id -> {
            System.out.println(id);
        });
        System.out.println("--------删除----------");
        needDelSet.forEach(id -> {
            System.out.println(id);
        });

        System.out.println("--------修改----------");
        needUdpSet.forEach(id -> {
            System.out.println(id);
        });
    }

    //取交集
    public static Collection<?> intersection(Collection<?> a, Collection<?> b) {
        HashSet tmp = new HashSet(a);
        tmp.retainAll(b);
        return tmp;
    }

    //取并集
    public static Collection<?> unionSet(Collection<?> a, Collection<?> b) {
        HashSet tmp = new HashSet(a);
        tmp.addAll(b);
        return tmp;
    }

    /**
     * 取补集 返回 b 中 a 没有的数据<br>
     * a[123] b[234]
     * @return 4
     */
    public static Collection<?> complementarySet(Collection<?> a, Collection<?> b) {
        HashSet tmp = new HashSet(a);
        tmp.addAll(b);
        tmp.removeAll(intersection(tmp, a));
        return tmp;
    }
}
