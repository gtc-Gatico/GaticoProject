package cn.com.gatico.entity;

public class TT {
    public static void main(String[] args) {
        C c = new C("h","k");
        System.out.println(c.name);
        System.out.println(c.getName());
        F f =(F)c;
        System.out.println(f.name);
        System.out.println(f.getName());
    }


}

class F {
    public String name;

    public String getName() {
        return name;
    }

    public F(String name) {
        this.name = name;
    }
}
class C extends F{
    public String name;

    public String getName() {
        return name;
    }

    public C(String name,String name1) {
        super(name1);
        this.name = name;
    }
}
