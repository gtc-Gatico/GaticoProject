package cn.com.gatico.entity;

public class CpeApplicationAclRuleMessage {
    private String srcCidr;

    private String dstCidr;

    private String application;

    private String action;

    public String getSrcCidr() {
        return srcCidr;
    }

    public void setSrcCidr(String srcCidr) {
        this.srcCidr = srcCidr;
    }

    public String getDstCidr() {
        return dstCidr;
    }

    public void setDstCidr(String dstCidr) {
        this.dstCidr = dstCidr;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CpeApplicationAclRuleMessage that = (CpeApplicationAclRuleMessage) o;
//        return this.getIdentity().equals(that.getIdentity());
        return hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return this.getIdentity().hashCode();
    }

//    public CpeApplicationAclRuleMessage clone() {
//        CpeApplicationAclRuleMessage cpeApplicationAclRuleMessage = new CpeApplicationAclRuleMessage();
//        cpeApplicationAclRuleMessage.setSrcCidr(this.srcCidr);
//        cpeApplicationAclRuleMessage.setDstCidr(this.dstCidr);
//        cpeApplicationAclRuleMessage.setApplication(this.application);
//        cpeApplicationAclRuleMessage.setAction(this.action);
//
//        return cpeApplicationAclRuleMessage;
//    }

    public String getIdentity(){
        return this.srcCidr + "-" + this.dstCidr + "-" + this.action + "-" + this.application;
    }

}
