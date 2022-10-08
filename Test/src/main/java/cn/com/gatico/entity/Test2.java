package cn.com.gatico.entity;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/11/19 10:29
 */
public class Test2 extends Test1 {
    public Test2() {
    }


    public Test2(Long t2) {
        this.t2 = t2;
    }

    private Long t2;

    public Long getT2() {
        return t2;
    }

    public void setT2(Long t2) {
        this.t2 = t2;
    }

    public static Object copy(Object src) {
        Object target = null;
        try {
            target = src.getClass().newInstance();
            Field[] srcFields = src.getClass().getDeclaredFields();
            for (int i = 0; i < srcFields.length; i++) {
                srcFields[i].setAccessible(true);
                Object v = srcFields[i].get(src);
                srcFields[i].set(target, v);
                srcFields[i].setAccessible(false);
            }
            return target;
        } catch (InstantiationException | IllegalAccessException  e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ABCDEFEntity abcdefEntity = new ABCDEFEntity();
        abcdefEntity.setId(1L);
        System.out.println(abcdefEntity);
        ABCDEFEntity copy = (ABCDEFEntity)Test2.copy(abcdefEntity);
        System.out.println(copy);
        System.out.println(copy.getId());

        Set<CpeApplicationAclRuleMessage> set = new HashSet<>();
        CpeApplicationAclRuleMessage cpeApplicationAclRuleMessage = new CpeApplicationAclRuleMessage();
        cpeApplicationAclRuleMessage.setApplication("0");
        for (int i = 1; i < 10; i++) {
            CpeApplicationAclRuleMessage copy1 = (CpeApplicationAclRuleMessage)Test2.copy(cpeApplicationAclRuleMessage);
            copy1.setApplication(i+"");
            set.add(copy1);
        }


        set.forEach(cpeApplicationAclRuleMessage1 -> {
            System.out.println(cpeApplicationAclRuleMessage1.getApplication());
        });

    }
}
