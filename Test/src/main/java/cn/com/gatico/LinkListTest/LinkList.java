package cn.com.gatico.LinkListTest;

public class LinkList<T> {

    private Link start;

    private Link end;

    private int length;

    public T add(T o) {
        if (start == null) {
            start = new Link();
        }
//        start.setValue(o);
        length++;
        return o;
    }


}
