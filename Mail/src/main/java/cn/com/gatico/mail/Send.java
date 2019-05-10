package cn.com.gatico.mail;


public class Send extends Thread {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }



    private String title;
    private String to;
    private String context;

    public MailService getMs() {
        return ms;
    }

    public void setMs(MailService ms) {
        this.ms = ms;
    }

    private MailService ms;
    @Override
    public void run() {
        this.ms.sendMail(this.to,this.title,this.context);
    }
}
