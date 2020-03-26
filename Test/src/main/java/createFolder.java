import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class createFolder {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("请设置配置文件路径");
            return;
        }
        String path = "";
        if (args[0] != null) {
            path = args[0];
        }
        String targetPath = "";
        if (args[1] != null) {
            targetPath = args[1];
        }
        File file = new File(Class.class.getResource("/").getPath() + path);
        try {
            File targetFile = new File(targetPath);
            if (targetFile.exists()) {
                List<String> paths = new ArrayList<>();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    paths.add(line);
                }
                bufferedReader.close();
                if (!paths.isEmpty() && paths.size() > 1) {
                    paths.set(0, paths.get(0).replace(".", "/"));
                    String tempPath = paths.get(0);
                    for (int i = 1; i < paths.size(); i++) {
                        paths.set(i, tempPath + (paths.get(i).replace("-", "/").replace(".", "/")));
                    }
                    for (int i = 0; i < paths.size(); i++) {
                        File folder = new File(targetFile.getPath() + "/" + paths.get(i));
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}