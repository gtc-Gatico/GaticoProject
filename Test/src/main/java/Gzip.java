

import org.apache.commons.io.compress.tar.TarEntry;
import org.apache.commons.io.compress.tar.TarInputStream;
import org.apache.commons.io.compress.tar.TarOutputStream;

import java.io.*;
import java.lang.String;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Gzip {
    public static void main(String[] args) {
        CompressedFiles_Gzip("D:\\logs", "D:\\var\\web.tar");
        unGZipFile("D:\\var\\web.tar.gz");
        unTarFile("D:\\var\\web.tar");
    }

    public static void unTarFile(String sourcedir) {
        try {
            //建立grip压缩文件输入流
            FileInputStream fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            TarInputStream tatIn = new TarInputStream(fin);
            for (int i = 0; i < tatIn.getRecordSize(); i++) {
                TarEntry nextEntry = tatIn.getNextEntry();
                if (nextEntry != null) {
                    //建立解压文件输出流
                    File tarF = new File(sourcedir.substring(0, sourcedir.lastIndexOf('.')) + File.separator + nextEntry.getName());
                    if (!tarF.getParentFile().exists()) {
                        tarF.getParentFile().mkdirs();
                    }
                    tarF.createNewFile();
                    FileOutputStream fout = new FileOutputStream(tarF);
                    byte[] buf = new byte[1024];
                    int num;
                    long sumSize = 0;
                    int readSize = buf.length;
                    while (sumSize <= nextEntry.getSize() && (num = tatIn.read(buf, 0, readSize)) != -1) {
                        fout.write(buf, 0, num);
                        sumSize += num;
                        readSize = nextEntry.getSize() - sumSize > buf.length ? buf.length : (int) (nextEntry.getSize() - sumSize);
                    }

                    fout.close();
                }
            }

            tatIn.close();
            fin.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void unGZipFile(String sourcedir) {
        try {
            //建立grip压缩文件输入流
            FileInputStream fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            GZIPInputStream gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            FileOutputStream fout = new FileOutputStream(sourcedir.substring(0, sourcedir.lastIndexOf('.')));
            byte[] buf = new byte[1024];
            int num;
            while ((num = gzin.read(buf, 0, buf.length)) != -1) {
                fout.write(buf, 0, num);
            }
            gzin.close();
            fout.close();

            fin.close();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * 压缩文件成Gzip格式，Linux上可使用
     * 压缩文件夹生成后缀名为".gz"的文件并下载
     *
     * @param folderPath,要压缩的文件夹的路径
     * @param targzipFilePath,压缩后文件的路径
     */
    public static void CompressedFiles_Gzip(String folderPath, String targzipFilePath) {
        File srcPath = new File(folderPath);
        int length = srcPath.listFiles().length;
        byte[] buf = new byte[1024]; //设定读入缓冲区尺寸
        File[] files = srcPath.listFiles();
        try {
            //建立压缩文件输出流
            FileOutputStream fout = new FileOutputStream(targzipFilePath);
            //建立tar压缩输出流
            TarOutputStream tout = new TarOutputStream(fout);
            for (int i = 0; i < length; i++) {
                String filename = srcPath.getPath() + File.separator + files[i].getName();
                //打开需压缩文件作为文件输入流
                FileInputStream fin = new FileInputStream(filename);   //filename是文件全路径
                TarEntry tarEn = new TarEntry(files[i]); //此处必须使用new TarEntry(File file);
                tarEn.setName(files[i].getName());  //此处需重置名称，默认是带全路径的，否则打包后会带全路径
                tout.putNextEntry(tarEn);
                int num;
                while ((num = fin.read(buf)) != -1) {
                    tout.write(buf, 0, num);
                }
                tout.closeEntry();
                fin.close();
            }
            tout.close();
            fout.close();

            //建立压缩文件输出流
            FileOutputStream gzFile = new FileOutputStream(targzipFilePath + ".gz");
            //建立gzip压缩输出流
            GZIPOutputStream gzout = new GZIPOutputStream(gzFile);
            //打开需压缩文件作为文件输入流
            FileInputStream tarin = new FileInputStream(targzipFilePath);   //targzipFilePath是文件全路径
            int len;
            while ((len = tarin.read(buf)) != -1) {
                gzout.write(buf, 0, len);
            }
            gzout.close();
            gzFile.close();
            tarin.close();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
