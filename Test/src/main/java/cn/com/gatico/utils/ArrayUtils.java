package cn.com.gatico.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayUtils {

    public static final String SEPARATOR = ",";

    public static String toString(Object[] array) {
        return Stream.of(array).map(Object::toString).collect(Collectors.joining(SEPARATOR));
//        return toString(array,StringUtils.EMPTY,SEPARATOR);
    }

    public static String toString(Object[] array,String stringIfNull,String separator) {
        if (null == array) {
            return stringIfNull;
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (; i < array.length; i++) {
            builder.append(array[i]).append(separator);
        }
        if (i > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    //取交集
    public static Collection<?> intersection(Collection<?> a,Collection<?> b) {
        HashSet tmp = new HashSet(a);
        tmp.retainAll(b);
        return tmp;
    }

    //取并集
    public static Collection<?> unionSet(Collection<?> a,Collection<?> b) {
        HashSet tmp = new HashSet(a);
        tmp.addAll(b);
        return tmp;
    }

    /**
     * 取补集 返回 b 中 a 没有的数据<br>
     * a[123] b[234]
     * @return 4
     */
    public static Collection<?> complementarySet(Collection<?> a,Collection<?> b) {
        HashSet tmp = new HashSet(a);
        tmp.addAll(b);
        tmp.removeAll(intersection(tmp,a));
        return tmp;
    }


}
