package cn.com.gatico.Tree;

public class TreeNodeEntity {
    private long id;
    private String name;
    private long upstreamId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUpstreamId() {
        return upstreamId;
    }

    public void setUpstreamId(long upstreamId) {
        this.upstreamId = upstreamId;
    }
}
