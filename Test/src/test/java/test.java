import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class test {
    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        m.insertAfter("{ System.out.println(\"Hello.say():end\"); }");
        Class c = cc.toClass();
        Hello h = (Hello) c.newInstance();
        h.say();

        CtMethod m2 = cc.getDeclaredMethod("main");
        m2.insertAfter("{ System.out.println(\"main start\"); }");
        h.main(args);
    }
}
