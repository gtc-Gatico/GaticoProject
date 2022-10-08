package cn.com.gatico.from;

public class UserItem {
    private String img;
    private String date;
    private String time;
    private String user;
    private String title;
    private String num;
    private String msg;

    public UserItem(String img, String date, String time, String user, String title, String num, String msg) {
        this.img = img;
        this.date = date;
        this.time = time;
        this.user = user;
        this.title = title;
        this.num = num;
        this.msg = msg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
