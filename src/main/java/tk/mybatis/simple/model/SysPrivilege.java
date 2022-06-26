package tk.mybatis.simple.model;

public class SysPrivilege {
    private long id;
    private String privilege_nam;
    private String privilege_url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrivilege_nam() {
        return privilege_nam;
    }

    public void setPrivilege_nam(String privilege_nam) {
        this.privilege_nam = privilege_nam;
    }

    public String getPrivilege_url() {
        return privilege_url;
    }

    public void setPrivilege_url(String privilege_url) {
        this.privilege_url = privilege_url;
    }
}
