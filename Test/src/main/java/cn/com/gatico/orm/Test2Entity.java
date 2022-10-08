package cn.com.gatico.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "global_dictionary")
public class Test2Entity {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "key")
    private String key;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Test2Entity{" +
                "id=" + id +
                ", key='" + key + '\'' +
                '}';
    }
}
