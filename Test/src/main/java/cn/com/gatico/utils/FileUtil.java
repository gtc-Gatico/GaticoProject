package cn.com.gatico.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lance on 4/29/17.
 */
public class FileUtil {

    private static final Logger logger = Logger.getLogger(FileUtil.class);

    public static List<File> listFiles(File path,String[]ext,boolean isChild){
        List<File> files = new ArrayList<>(Arrays.stream(Objects.requireNonNull(path.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith("ext");
            }
        }))).collect(Collectors.toList()));

       if(isChild){
           files.forEach(file -> {
               files.addAll(listFiles(file, ext, isChild));
           });
       }
        return files;
    }
    public static List<String> readLines(String filePath) {

        BufferedReader br = null;
        boolean result = false;
        try {

            if (StringUtils.isBlank(filePath))
                return null;

            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));

            List<String> lines = new ArrayList();
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (FileNotFoundException fnfe) {
            logger.error(filePath + " is not found", fnfe);
        } catch (IOException ioe) {
            logger.error(filePath + " can not be operated", ioe);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                logger.error(filePath + " can not be closed", ioe);
            }
        }
        return null;
    }

    public static synchronized boolean writeLines(String fileName, List<String> lineList, boolean append) {
        FileDescriptor fd = null;
        BufferedWriter bw = null;
        boolean result = false;
        try {

            File file = new File(fileName);
            if (!append && file.exists()) {
                file.delete();
            }
            file.createNewFile();

            fd = new FileOutputStream(file, append).getFD();
            bw = new BufferedWriter(new FileWriter(fd));

            int i = 0;
            for (String line : lineList) {
                if (i != 0) {
                    bw.newLine();
                }
                bw.write(line);
                i++;
            }

            bw.newLine();

            result = true;
        } catch (FileNotFoundException fnfe) {
            logger.error(fileName + " is not found", fnfe);
        } catch (IOException ioe) {
            logger.error(fileName + " can not be operated", ioe);
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    fd.sync();
                    bw.close();
                }
            } catch (IOException ioe) {
                logger.error(fileName + " can not be closed", ioe);
            }
        }

        return result;
    }

    public boolean write(String fileName, String lines, boolean append) {
        BufferedWriter bw = null;
        boolean result = false;
        try {

            File file = new File(fileName);
            if (!append && file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file.getPath(), append));
            bw.write(lines);
            result = true;
        } catch (FileNotFoundException fnfe) {
            logger.error(fileName + " is not found", fnfe);
        } catch (IOException ioe) {
            logger.error(fileName + " can not be operated", ioe);
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException ioe) {
                logger.error(fileName + " can not be closed", ioe);
            }
        }

        return result;
    }

    public String read(String filePath) {
        BufferedReader br = null;
        try {
            if (StringUtils.isBlank(filePath)){
                return null;
            }
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));
            StringBuffer lines = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                lines.append(line);
            }
            return lines.toString();
        } catch (FileNotFoundException fnfe) {
            logger.error(filePath + " is not found", fnfe);
        } catch (IOException ioe) {
            logger.error(filePath + " can not be operated", ioe);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                logger.error(filePath + " can not be closed", ioe);
            }
        }
        return null;
    }
}
