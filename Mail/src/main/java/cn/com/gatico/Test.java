package cn.com.gatico;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.ParseException;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        //tianci.gao@7x-networks.com;jimmy.guo@watsons.com.cn
        String mail="jimmy.guo@watsons.com.cn";
        Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?'{|}~^-]+(?:\\.[\\w!#$%&'*+/=?'{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(mail);
        System.out.println(matcher.matches());

       /* ContentType ct = null;
        try {
            ct = new ContentType("application/vnd.ms-excel");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String charset = ct.getParameter("charset");
        System.out.println(charset);*/
        try {
            /*
            Properties properties = new Properties();
            properties.setProperty("mail.debug", "true");// 是否显示调试信息(可选)
            properties.setProperty("mail.smtp.starttls.enable", "false");
            properties.setProperty("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.smtp.auth", "true");
            properties.put("mail.smtp.timeout ", " 25000 ");

            String []tos = new String[]{"48909084@qq.com"};
            String host = "smtp.qq.com";
            String username = "48909084@qq.com";
            String password = "wbgzktpzagjkbhhb";
            File osFile = new File("/home/tianci.gao/test/test.PNG");
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            //mailSender.setJavaMailProperties(properties);
            mailSender.setUsername(username);
            mailSender.setPassword(password);
            mailSender.setHost(host);
            mailSender.setDefaultEncoding("UTF-8");
            //mailSender.setPort(465);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true);// 第二个参数设置为true，表示允许添加附件
            mailHelper.setFrom(username);
            mailHelper.setTo(tos);
            mailHelper.setSubject("测试");
            mailHelper.setText("", false);
            */
            /*FileSystemResource file = new FileSystemResource(new File(userFile.getPath()));
            String AttachName = MimeUtility.encodeText("用户使用记录报表.xlsx");
            mailHelper.addAttachment(AttachName, file);*//*
            //System.setProperty("mail.mime.splitlongparameters", "false");
            byte[]arr=new byte[1024];
            mailHelper.addAttachment("测试.PNG", osFile);
            mailHelper.addAttachment(MimeUtility.encodeText("用户使用记录报表_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx","utf-8","B"),new ByteArrayDataSource(arr,"application/vnd.ms-excel"));
            mailHelper.addAttachment(MimeUtility.encodeText("店铺人流量统计表_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx","utf-8","B"),new  ByteArrayDataSource(arr,"application/vnd.ms-excel"));
            mailHelper.addAttachment(MimeUtility.encodeText("设备操作系统统计表_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx","utf-8","B"),new  ByteArrayDataSource(arr,"application/vnd.ms-excel"));
            mailSender.send(mimeMessage);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
