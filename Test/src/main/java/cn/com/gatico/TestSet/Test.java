package cn.com.gatico.TestSet;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Test {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("d");

        System.out.println(list.stream().collect(Collectors.groupingBy(s -> s)).values().stream().filter(strings -> strings.size() >= 2).count());
        System.out.println(list.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting())));


        System.exit(1);
        Map<String, Integer> map1 = new HashMap<>();
        list.forEach(s -> {
            Integer orDefault = map1.getOrDefault(s, 0);
            map1.put(s, ++orDefault);
        });
        Map<String, Integer> list1 = new HashMap<>();
        map1.forEach((s, integer) -> {
            if (integer >= 2) {
                list1.put(s, integer);
            }
        });

        list1.forEach((s, integer) -> {
            System.out.println(s);
        });
        System.exit(1);
        System.out.println(new String(Base64.getDecoder().decode("BUY="), StandardCharsets.UTF_8));

        System.exit(1);
        String str = "1111";
        long s, e;
        int c = 1000_0000;
        s = System.currentTimeMillis();
        for (int i = 0; i < c; i++) {
            boolean b = "".equals(str);
        }
        e = System.currentTimeMillis();
        System.out.println(e - s);//5

        s = System.currentTimeMillis();
        for (int i = 0; i < c; i++) {
            boolean b = str.length() == 0;
        }
        e = System.currentTimeMillis();
        System.out.println(e - s);//3


        s = System.currentTimeMillis();
        for (int i = 0; i < c; i++) {
            boolean b = str.isEmpty();
        }
        e = System.currentTimeMillis();
        System.out.println(e - s);//3

        s = System.currentTimeMillis();
        for (int i = 0; i < c; i++) {
            boolean b = str == "";
        }
        e = System.currentTimeMillis();
        System.out.println(e - s);//2


        Map<Long, Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        set.add("1,1,1,111,1");
        map.put(1L, set);
        System.out.println(map);

//        map.get(1L).remove("1,1,1,111,1");
        test2(map, "1,1,1,111,1");
        System.out.println(map);

    }

    public static void test2(Map<Long, Set<String>> map, String k) {
        map.get(1L).remove(k);
    }

    public void test1() {
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
     *
     * @return 4
     */
    public static Collection<?> complementarySet(Collection<?> a, Collection<?> b) {
        HashSet tmp = new HashSet(a);
        tmp.addAll(b);
        tmp.removeAll(intersection(tmp, a));
        return tmp;
    }
}
