package cn.com.gatico.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/10/25 12:43
 */
public class aaab_TwoNumSum {
    ///////////////////////////////////////////////////////////////////////////
    // 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    //
    //如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    //
    //您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    //
    //示例：
    //
    //输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    //输出：7 -> 0 -> 8
    //原因：342 + 465 = 807
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/add-two-numbers
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(3);
        ListNode listNode2 = new ListNode(59);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);
        ListNode res = addTwoNumbers(listNode1, listNode2);
        List<Integer> resArr = new ArrayList<>();
        LN2List(res, resArr);
        resArr.stream().forEach(integer -> System.out.println(integer));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        List t1 = new ArrayList();
        LN2List(l1, t1);
        List t2 = new ArrayList();
        LN2List(l2, t2);

        if (t2.size() > t1.size()) {
            ListNode max = l2;
            l2 = l1;
            l1 = max;
        }
        return Add(l1, l2, l3);
    }

    public static ListNode Add(ListNode l1, ListNode l2, ListNode l3) {
        int tmp = l3.val;
        if (l1 != null || l2 != null) {
            if (l1 == null) {
                l1 = new ListNode(0);
            }
            if (l2 == null) {
                l2 = new ListNode(0);
            }
            if (tmp + l1.val + l2.val >= 10) {
                l3 = new ListNode((tmp + l1.val + l2.val) % 10);
                l3.next = new ListNode((Integer) (tmp + l1.val + l2.val) / 10);
                l3.next = Add(l1.next, l2.next, l3.next);
            } else {
                l3 = new ListNode(tmp + (l1.val + l2.val));
                l3.next = new ListNode(0);
                l3.next = Add(l1.next, l2.next, l3.next);
            }
        } else {
            if (tmp == 0) {
                l3 = null;
            }
        }
        return l3;
    }


    public static void LN2List(ListNode l1, List list) {
        list.add(l1.val);
        if (l1.next != null) {
            LN2List(l1.next, list);
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
