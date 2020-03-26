package cn.com.gatico.反射;

import cn.com.gatico.反射.TestClass.Urls;
import cn.com.gatico.反射.annotattions.API;
import cn.com.gatico.反射.annotattions.Mapping;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    private static final String EXT = "class";

    public static void main(String[] args) throws Exception {
        Map<String, Object> pMap = new HashMap<>();
        pMap.put("str", "666777");
        Set<Class<?>> classSet = Scanner.getClasses(Test.class.getPackage().getName());
        System.out.println(classSet.size());
        Set<Class<?>> apis = new HashSet<>();
        classSet.forEach(aClass -> {
            if (aClass.getAnnotation(API.class) != null) {
                apis.add(aClass);
            }
        });
        System.out.println(apis.size());
        Set<Urls> urlsSet = new HashSet<>();
        apis.forEach(aClass -> {
            Method[] methods = aClass.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Mapping mapping = methods[i].getAnnotation(Mapping.class);
                if (mapping != null) {
                    Urls urls = new Urls();
                    urls.setMethod(mapping.method());
                    urls.setUrl(mapping.url());
                    urls.setExecMethod(methods[i]);
                    urls.setClazz(aClass);
                    urlsSet.add(urls);
                }
            }
        });

        urlsSet.forEach(urls -> {
            System.out.println(urls.getUrl());
            System.out.println(urls.getMethod());
            System.out.println(urls.getExecMethod());
            System.out.println(urls.getClazz());
            try {
                Method method = urls.getExecMethod();
                System.out.println(method.getName());
                System.out.println(method.getReturnType());
                Object type = method.getGenericReturnType();
                System.out.println(type);
                Object[] param = new Object[method.getParameterTypes().length];
                for (int i = 0; i < method.getParameters().length; i++) {
                    String paramName = method.getParameters()[i].getName();
                    param[i] = pMap.get(paramName);
                }
                Object clazz = urls.getClazz().newInstance();
                String json = JSONObject.toJSONString(method.invoke(clazz, param));
                System.out.println(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * 获取指定包下包含指定注解的所有类对象的集合
     *
     * @param pkgName           包名(com.demo.controller)
     * @param pkgPath           包路径(/Users/xxx/workspace/java/project/out/production/classes/com/demo/controller)
     * @param recursive         是否递归遍历子目录
     * @param targetAnnotations 指定注解
     * @return 以注解和对应类集合构成的键值对
     */
    public static Map<Class<? extends Annotation>, Set<Class<?>>> scanClassesByAnnotations(
            String pkgName, String pkgPath, final boolean recursive, List<Class<? extends Annotation>> targetAnnotations) {
        Map<Class<? extends Annotation>, Set<Class<?>>> resultMap = new HashMap<>(16);

        Collection<File> allClassFile = getAllClassFile(pkgPath, recursive);

        for (File curFile : allClassFile) {
            try {
                Class<?> curClass = getClassObj(curFile, pkgPath, pkgName);
                for (Class<? extends Annotation> annotation : targetAnnotations) {
                    if (curClass.isAnnotationPresent(annotation)) {
                        if (!resultMap.containsKey(annotation)) {
                            resultMap.put(annotation, new HashSet<Class<?>>());
                        }
                        resultMap.get(annotation).add(curClass);
                    }
                }
            } catch (ClassNotFoundException e) {
                logger.error("load class fail", e);
            }
        }

        return resultMap;
    }

    /**
     * 加载类
     *
     * @param file
     * @param pkgPath
     * @param pkgName
     * @return
     * @throws ClassNotFoundException
     */
    private static Class<?> getClassObj(File file, String pkgPath, String pkgName) throws ClassNotFoundException {
        // 考虑class文件在子目录中的情况
        String absPath = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - EXT.length() - 1);
        String className = absPath.substring(pkgPath.length()).replace(File.separatorChar, '.');
        className = className.startsWith(".") ? pkgName + className : pkgName + "." + className;

        return Thread.currentThread().getContextClassLoader().loadClass(className);
    }

    /**
     * 遍历指定目录下所有扩展名为class的文件
     *
     * @param pkgPath   包目录
     * @param recursive 是否递归遍历子目录
     * @return
     */
    private static Collection<File> getAllClassFile(String pkgPath, boolean recursive) {
        File fPkgDir = new File(pkgPath);

        if (!(fPkgDir.exists() && fPkgDir.isDirectory())) {
            logger.error("the directory to package is empty: {}", pkgPath);

            return null;
        }

        return FileUtils.listFiles(fPkgDir, new String[]{EXT}, recursive);
    }

    /**
     * 查找指定注解的Method
     *
     * @param classes           查找范围
     * @param targetAnnotations 指定的注解
     * @return 以注解和对应Method类集合构成的键值对
     */
    public static Map<Class<? extends Annotation>, Set<Method>> scanMethodsByAnnotations(Set<Class<?>> classes,
                                                                                         List<Class<? extends Annotation>> targetAnnotations) {
        Map<Class<? extends Annotation>, Set<Method>> resultMap = new HashMap<>(16);

        for (Class<?> cls : classes) {
            Method[] methods = cls.getMethods();

            for (Class<? extends Annotation> annotation : targetAnnotations) {
                for (Method method : methods) {
                    if (method.isAnnotationPresent(annotation)) {
                        if (!resultMap.containsKey(annotation)) {
                            resultMap.put(annotation, new HashSet<Method>());
                        }
                        resultMap.get(annotation).add(method);
                    }
                }
            }
        }

        return resultMap;
    }
}
