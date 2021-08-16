import cn.com.gatico.entity.ABCDEFEntity;
import cn.com.gatico.utils.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Hello {
    public void say() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {

        int[] intArr = new int[]{9, 5, 2, 7};
        Integer[] integerArr = new Integer[intArr.length];
        IntStream.of(intArr).boxed().collect(Collectors.toList()).toArray(integerArr);
        Arrays.toString(integerArr);


        int arr[] = new int[]{1, 2, 3};
//        newArr(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();

        List<ABCDEFEntity> abcdefEntities = new ArrayList<>();
        abcdefEntities.add(new ABCDEFEntity(3L, "hhh"));
        abcdefEntities.add(new ABCDEFEntity(2L, "jjj"));
        abcdefEntities.add(new ABCDEFEntity(5L, "kkk"));
        abcdefEntities.add(new ABCDEFEntity(4L, "lll"));
        ABCDEFEntity nm1 = Collections.min(abcdefEntities, Comparator.comparing(ABCDEFEntity::getAction));
        System.out.println(nm1.getId());

        Map<Long, ABCDEFEntity> strMap1 = abcdefEntities.stream().collect(Collectors.toMap(ABCDEFEntity::getId, abcdefEntity -> abcdefEntity));
        strMap1.forEach((aLong, abcdefEntity) -> {
            System.out.println(aLong + "," + abcdefEntity.getAction());
        });

        System.out.println(StringUtils.isEmail("yuan_0842@163.com"));
    }

    public static void newArr(int arr[]) {
        arr[0] = 10;
        arr = new int[]{1, 2, 3, 4};
    }
}
