package cn.com.gatico.entity;

import javax.persistence.*;

@Entity
@Table(name = "ms_userinfo")
public class MsUserinfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "uuid")
    private String uuid;

    @Basic
    @Column(name = "addr")
    private String addr;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
