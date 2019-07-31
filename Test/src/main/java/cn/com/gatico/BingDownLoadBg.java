package cn.com.gatico;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BingDownLoadBg {
    public static void main(String[] args) throws Exception {
        String path = "/home/bing";
        if (null != args) {
            path = args[0];
        }
        File cnjson = new File(path+"/img-cn");
        File enjson = new File(path+"/img-en");
        FileWriter cnfw;
        FileWriter enfw;
        if(!cnjson.exists()){
            cnjson.mkdir();
        }
        if(!enjson.exists()){
            enjson.mkdir();
        }
        while (true) {
            cnfw = new FileWriter(cnjson,true);
            enfw = new FileWriter(enjson,true);
            String[] urls = {"https://cn.bing.com", "https://cn.bing.com/?ensearch=1&FORM=BEHPTB"};
            for (int i = 0; i < urls.length; i++) {
                URL url = new URL(urls[i]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String tempStr = bufferedReader.readLine();
                while (tempStr != null) {
                    stringBuffer.append(tempStr);
                    tempStr = bufferedReader.readLine();
                }
                String html = stringBuffer.toString();
                int startPosition = html.indexOf("/th?id=OHR.");
                int endPosition = html.indexOf("_1920x1080.jpg&amp;rf=LaDigue_1920x1080.jpg&amp;pid=hp");
                String bgurl = html.substring(startPosition, endPosition);
                bgurl = "https://cn.bing.com" + bgurl + "_1920x1080.jpg&amp;rf=LaDigue_1920x1080.jpg&amp;pid=hp";
                URL bgURL = new URL(bgurl);
                URLConnection urlConnection = bgURL.openConnection();
                InputStream bgInputStream = urlConnection.getInputStream();
                String fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                File file = new File(path + "/" + (i == 0 ? "cn" : "en") + "/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath() + "/" + fileName + ".jpg");
                int bit;
                while ((bit = bgInputStream.read()) != -1) {
                    fileOutputStream.write(bit);
                }
                if(i==0){
                    cnfw.append(","+System.lineSeparator()+"{\"time\":\""+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"\",\"path\":\""+  "cn" + "/"+fileName + ".jpg"+"\"}");
                    cnfw.flush();
                    cnfw.close();
                }else{
                    enfw.append(","+System.lineSeparator()+"{\"time\":\""+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"\",\"path\":\""+  "en" + "/"+fileName + ".jpg"+"\"}");
                    enfw.flush();
                }
            }
            Thread.sleep(TimeUnit.HOURS.toMillis(24));
        }

    }
}
