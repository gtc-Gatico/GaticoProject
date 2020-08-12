package cn.com.gatico.LinkListTest;

public class Link {
    private Link prev;
    private Link next;
    private Object value;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Link() {
    }

    public Link(Object value) {
        this.value = value;
    }

    public Link getPrev() {
        return prev;
    }

    public void setPrev(Link prev) {
        this.prev = prev;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[" + this.id + "," + this.value + "]->");
        return sb.toString();
    }
}
