import cn.com.gatico.entity.ABCDEFEntity;
import cn.com.gatico.utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hello {
    public void say() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {

        List<ABCDEFEntity> abcdefEntities = new ArrayList<>();
        abcdefEntities.add(new ABCDEFEntity(3L, "hhh"));
        abcdefEntities.add(new ABCDEFEntity(2L, "jjj"));
        abcdefEntities.add(new ABCDEFEntity(5L, "kkk"));
        abcdefEntities.add(new ABCDEFEntity(4L, "lll"));
        ABCDEFEntity nm1 = Collections.min(abcdefEntities, Comparator.comparing(ABCDEFEntity::getAction));
        System.out.println(nm1.getId());

        Map<Long, ABCDEFEntity> strMap1 = abcdefEntities.stream().collect(Collectors.toMap(abcdefEntity -> abcdefEntity.getId(), abcdefEntity -> abcdefEntity));
        strMap1.forEach((aLong, abcdefEntity) -> {
            System.out.println(aLong+","+abcdefEntity.getAction());
        });

        System.out.println(StringUtils.isEmail("yuan_0842@163.com"));
    }
}
