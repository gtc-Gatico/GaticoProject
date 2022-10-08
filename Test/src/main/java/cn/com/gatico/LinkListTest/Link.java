package cn.com.gatico.LinkListTest;

public class Link<T> {
    private Link<T> prev;
    private Link<T> next;
    private T value;

   public void add(T t){
       this.value = t;
   }

    @Override
    public String toString() {
        return "[" + this.value + "]->";
    }
}
