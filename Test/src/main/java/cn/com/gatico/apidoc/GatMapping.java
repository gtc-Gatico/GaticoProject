package cn.com.gatico.apidoc;

import javax.swing.*;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GatMapping {

    public static void main(String[] args) throws Exception{
        String jarPath = "D:\\Project\\Java\\beacon\\target\\classes\\";
        Map<String,Class<?>>  classes = loadClasses(jarPath);

        JFrame jFrame = new JFrame("获取字段");
        jFrame.setSize(500,500);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);// 设置居中显示
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(jFrame);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel beanLabel = new JLabel("Bean：");
        beanLabel.setBounds(10, 10, 70, 20);
        jFrame.add(beanLabel);
        JTextField beanTextArea = new JTextField();
        beanTextArea.setBounds(70, 10, 250, 40);
        jFrame.add(beanTextArea);
        JButton jButton = new JButton("生成");
        jButton.setBounds(370, 10, 70, 40);
        jFrame.add(jButton);

        JLabel textLabel = new JLabel("生成文本：");
        textLabel.setBounds(10, 80, 70, 20);
        jFrame.add(textLabel);
        JScrollPane jScrollPane = new JScrollPane();
        JTextArea beanResArea = new JTextArea();
        jScrollPane.setBounds(70, 80, 400, 300);
        jScrollPane.setViewportView(beanResArea);
        jFrame.add(jScrollPane);

        StringBuffer stringBuffer = new StringBuffer();
        jButton.addActionListener(e -> {
            if(!beanTextArea.getText().isEmpty()){
                stringBuffer.setLength(0);
                Class<?> aClass = classes.get(beanTextArea.getText());
                Field[] declaredFields = aClass.getDeclaredFields();
                int maxLength = 0;
                int paramMaxLenght = 0;
                for (int i = 0; i < declaredFields.length; i++) {
                    maxLength = Math.max(maxLength, declaredFields[i].getName().length());
                    paramMaxLenght = Math.max(paramMaxLenght, declaredFields[i].getType().getSimpleName().length());
                }
                if(beanTextArea.getText().contains("Bean")) {
                    stringBuffer.append(String.format("|%-" + maxLength + "s|Required\t|%-" + paramMaxLenght + "s|Default\t|Description|\n", "Key", "Type", ""));
                    stringBuffer.append(String.format("|%-" + maxLength + "s|:----\t\t|%-" + paramMaxLenght + "s|:----\t|:----\t\t|\n", ":----", ":----", ""));
                    stringBuffer.append(String.format("|%-" + maxLength + "s|%s\t\t|%-" + paramMaxLenght + "s| \t\t|%s\t|\n", "access_token", "True", "String","访问令牌"));
                }else if(beanTextArea.getText().contains("Vo")){
                    stringBuffer.append(String.format("|%-" + maxLength + "s|%-" + paramMaxLenght + "s\t|%s|\n", "Key", "Type", "Description"));
                    stringBuffer.append(String.format("|%-" + maxLength + "s|%-" + paramMaxLenght + "s\t|%s|\n", ":----", ":----", ":----\t"));
                }
                for (int i = 0; i < declaredFields.length; i++) {
                    if(beanTextArea.getText().contains("Bean")){
                        stringBuffer.append(String.format("|%-"+maxLength+"s|True\t\t|%-"+paramMaxLenght+"s| \t\t|%s|\n",declaredFields[i].getName(),declaredFields[i].getType().getSimpleName(),""));
                    }else if(beanTextArea.getText().contains("Vo")){
                        stringBuffer.append(String.format("|%-"+maxLength+"s|%-"+paramMaxLenght+"s\t|%s|\n",declaredFields[i].getName(),declaredFields[i].getType().getSimpleName(),""));
                    }
                    beanResArea.setText(stringBuffer.toString());
                }
            }
        });

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public static Map<String,Class<?>> loadClasses(String rootClassPath) throws Exception {
        Map<String,Class<?>> classSet = new HashMap<>();
        // 设置class文件所在根路径
        File clazzPath = new File(rootClassPath);

        // 记录加载.class文件的数量
        int clazzCount = 0;

        if (clazzPath.exists() && clazzPath.isDirectory()) {
            // 获取路径长度
            int clazzPathLen = clazzPath.getAbsolutePath().length() + 1;

            Stack<File> stack = new Stack<>();
            stack.push(clazzPath);

            // 遍历类路径
            while (!stack.isEmpty()) {
                File path = stack.pop();
                File[] classFiles = path.listFiles(new FileFilter() {
                    public boolean accept(File pathname) {
                        //只加载class文件
                        return pathname.isDirectory() || (pathname.getName().endsWith(".class")
                                && (pathname.getPath().contains("com\\sevenXnetworks\\beacon\\bean")
                                || pathname.getPath().contains("com\\sevenXnetworks\\beacon\\controller")
                                || pathname.getPath().contains("com\\sevenXnetworks\\beacon\\vo")
                                || pathname.getPath().contains("com\\sevenXnetworks\\beacon\\service")))
                                ;
                    }
                });
                if (classFiles == null) {
                    break;
                }
                for (File subFile : classFiles) {
                    if (subFile.isDirectory()) {
                        stack.push(subFile);
                    } else {
                        if (clazzCount++ == 0) {
                            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                            boolean accessible = method.isAccessible();
                            try {
                                if (!accessible) {
                                    method.setAccessible(true);
                                }
                                // 设置类加载器
                                URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                                // 将当前类路径加入到类加载器中
                                method.invoke(classLoader, clazzPath.toURI().toURL());
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                method.setAccessible(accessible);
                            }
                        }
                        // 文件名称
                        String className = subFile.getAbsolutePath();
                        className = className.substring(clazzPathLen, className.length() - 6);
                        //将/替换成. 得到全路径类名
                        className = className.replace(File.separatorChar, '.');
                        // 加载Class类
                        Class<?> aClass = Class.forName(className);
                        classSet.put(className.substring(className.lastIndexOf(".")+1),aClass);
                        System.out.println("读取应用程序类文件[class={" + className + "}]");
                    }
                }
            }
        }
        return classSet;
    }

}
