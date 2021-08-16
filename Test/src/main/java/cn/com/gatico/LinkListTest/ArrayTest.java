package cn.com.gatico.LinkListTest;

import java.util.LinkedList;
import java.util.List;

public class ArrayTest {
    public static void main(String[] args) {
        Link link1 = new Link(1);
        Link link2 = new Link(2);
        link1.setNext(link2);
        link2.setPrev(link1);
        Link link3 = new Link(3);
        link2.setNext(link3);
        link3.setPrev(link2);

        List<Link> list = new LinkedList<>();
        Link tmp = link1;
        while (tmp != null) {
            list.add(tmp);
            tmp = tmp.getNext();
        }
        Link s = link1;
        while (s != null) {
            System.out.println(s.getValue());
            s = s.getNext();
        }
        list.forEach(link -> {
            Link next = link.getNext();
            Link prev = link.getPrev();
            link.setNext(prev);
            link.setPrev(next);
        });
        Link link = list.get(list.size() - 1);

        while (link != null) {
            System.out.println(link.getValue());
            link = link.getNext();
        }
    }
}
