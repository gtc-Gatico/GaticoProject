import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        Stream.of("abcadefeca".split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .forEachOrdered(e -> System.out.print(e.getKey()));


        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Collectors.joining(","));
        //String s = String.join(",", Arrays.toString(arr));//.join(",");
        String s = IntStream.of(arr).mapToObj(value -> "" + value).collect(Collectors.joining(","));
        System.out.println(s);

        String str = "aabbcca";


        arr = new int[]{2, 2, 1, 1, 1, 2, 2};

        Map<Integer, Integer> resMap = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (resMap.containsKey(arr[i])) {
                resMap.put(arr[i], (resMap.get(arr[i]) + 1));
                if (resMap.get(arr[i]) > arr.length / 2) {
                    //return;  arr[i];
                }
            } else {
                resMap.put(arr[i], 1);
            }
        }
        //System.out.println(resMap.values().stream().max((o1, o2) -> new BigDecimal(o1).compareTo(new BigDecimal(o2))).get());
        // System.out.println((getFirst(sortByValue(resMap)).getValue()));
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();
        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public static <K, V> Map.Entry<K, V> getFirst(Map<K, V> map) {
        return map.entrySet().iterator().next();
    }


}
