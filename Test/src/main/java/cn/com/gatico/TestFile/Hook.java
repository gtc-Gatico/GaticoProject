package cn.com.gatico.TestFile;

import cn.com.gatico.classloader.FileSystemClassLoader;
import com.sun.javafx.collections.MappingChange;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hook {
    // 需要监听的文件目录（只能监听目录）
    static String path = "D:\\File\\class";
    static Map<String, Class<?>> classMap = new HashMap<>();

    public static void main(String[] args) throws IOException {


        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path p = Paths.get(path);
        p.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.OVERFLOW
        );
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    WatchKey watchKey = watchService.take();
                    List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                    for (WatchEvent<?> event : watchEvents) {
                        //TODO 根据事件类型采取不同的操作。。。。。。。
                        Path name = ((WatchEvent<Path>) event).context();
                        Path child = p.resolve(name);
                        //f 是p下面的文件夹或者目录，下面的文件自己处理
                        File f = child.toFile();
                        System.out.println("[" + f.getAbsolutePath() + "]文件发生了[" + event.kind() + "]事件");
                        if (f.getName().endsWith(".class")) {
                            if (event.kind().name().equals("ENTRY_CREATE") || event.kind().name().equals("ENTRY_MODIFY") || event.kind().name().equals("ENTRY_DELETE")) {
                                menu1();
                            }
                            System.out.println("Java Class:" + f.getName());
                        }
                    }
                    watchKey.reset();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(false);
        thread.start();

        // 增加jvm关闭的钩子来关闭监听
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                watchService.close();
            } catch (Exception e) {
            }
        }));

        menu();
        menu1();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.equals("1") || next.equals("r")) {
                menu1();
            } else if (next.equals("2")) {
                menu2();
            } else if (next.equals("3")) {
                System.out.print("请输入查看的类的序号：");
                int classIndex = scanner.nextInt();
                Object[] objects = classMap.keySet().toArray();
                menu3(objects[classIndex - 1].toString());
            } else if (next.equals("4")) {
                System.out.print("请输入执行的类的序号：");
                int classIndex = scanner.nextInt();
                Object[] objects = classMap.keySet().toArray();
                System.out.print("请输入" + objects[classIndex - 1] + "的方法：");
                int methodIndex = scanner.nextInt();
                String methodName = classMap.get(objects[classIndex - 1].toString()).getDeclaredMethods()[methodIndex - 1].getName();
                Method declaredMethod = classMap.get(objects[classIndex - 1].toString()).getDeclaredMethods()[methodIndex - 1];
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                Object[] para = new Object[parameterTypes.length];
                if (parameterTypes.length != 0) {
                    for (int i = 0; i < parameterTypes.length; i++) {
                        System.out.print("请输入" + methodName + "方法的参数(共" + parameterTypes.length + "个,第"+(i+1)+"个)：");
                        if (parameterTypes[i].getName().equals("char")) {
                            para[i] = parameterTypes[i].cast(scanner.next().charAt(0));
                        } else if (parameterTypes[i].getName().equals("boolean")) {
                            para[i] = scanner.nextBoolean();
                        } else if (parameterTypes[i].getName().equals("byte")) {
                            para[i] = scanner.nextByte();
                        } else if (parameterTypes[i].getName().equals("short")) {
                            para[i] = scanner.nextShort();
                        } else if (parameterTypes[i].getName().equals("int")) {
                            para[i] = parameterTypes[i].cast(scanner.nextInt());
                        }else if (parameterTypes[i].getName().equals("long")) {
                            para[i] = scanner.nextLong();
                        } else if (parameterTypes[i].getName().equals("float")) {
                            para[i] = scanner.nextFloat();
                        } else if (parameterTypes[i].getName().equals("double")) {
                            para[i] = scanner.nextDouble();
                        }else{
                            para[i] = parameterTypes[i].cast(scanner.next().split(" "));
                        }
                    }
                }
                Object obj = menu4(objects[classIndex - 1].toString(), methodName, para);
                System.out.println(obj);
            }
            menu();
        }
    }

    public static void menu() {
        System.out.println("\nJAVA 监听 Class 文件变化\n" +
                "\n" +
                "1：重新加载文件\n" +
                "2：已加载文件列表\n" +
                "3：查看类方法\n" +
                "4：执行方法\n" +
                "\n" +
                "请输入序号：");

    }

    public static void menu1() {
        FileSystemClassLoader fileSystemClassLoader = new FileSystemClassLoader(path);
        try {
            List<Path> pathStream = Files.list(Paths.get(path)).collect(Collectors.toList());
            pathStream.forEach(path1 -> {
                String name = path1.toFile().getName();
                if (name.endsWith("class")) {
                    try {
                        name = name.replace(".class", "");
                        Class<?> aClass = fileSystemClassLoader.loadClass(name);
                        classMap.put(aClass.getName(), aClass);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void menu2() {
        AtomicInteger i = new AtomicInteger(0);
        classMap.forEach((s, aClass) -> {
            System.out.println(i.addAndGet(1) + ":" + s);

        });
    }

    public static void menu3(String className) {
        Method[] declaredMethods = classMap.get(className).getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.print((i + 1) + ":方法名：" + declaredMethods[i].getName() + "\t返回值：" + declaredMethods[i].getReturnType());
            Class<?>[] parameterTypes = declaredMethods[i].getParameterTypes();
            System.out.print("\t参数名：");
            for (int j = 0; j < parameterTypes.length; j++) {
                System.out.print(parameterTypes[j].getName() + "\t");
            }
            System.out.println();
        }
    }

    public static Object menu4(String className, String methodName, Object[] parametr) {
        Class<?> aClass = classMap.get(className);
        try {
            Object o = aClass.newInstance();
            Class<?>[] type = new Class[parametr.length];
            for (int i = 0; i < parametr.length; i++) {
                type[i] = parametr[i].getClass();
            }
            Method method = aClass.getMethod(methodName, type);
            return method.invoke(o, parametr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
