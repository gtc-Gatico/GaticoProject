package cn.com.gatico.TestSet;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
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
}
