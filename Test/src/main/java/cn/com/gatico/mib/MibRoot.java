package cn.com.gatico.mib;

import java.util.ArrayList;
import java.util.List;

public class MibRoot {
    private String name;
    private String title;
    private String type = "root";
    private String lastUpdate="";
    private String organization="";
    private String contactInfo="";
    private List<MibTree> children = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.title = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<MibTree> getChildren() {
        return children;
    }

    public void setChildren(List<MibTree> children) {
        this.children = children;
    }

}
