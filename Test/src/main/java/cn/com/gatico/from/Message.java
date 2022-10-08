package cn.com.gatico.from;

public class Message {

    public Message() {
    }

    public Message(String msg, String img, String type) {
        this.msg = msg;
        this.img = img;
        this.type = type;
    }

    private String type;
    private String msg;
    private String img;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}

