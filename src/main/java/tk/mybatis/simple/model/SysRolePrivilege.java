package tk.mybatis.simple.model;

public class SysRolePrivilege {
    private long role_id;
    private long privilege_id;

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public long getPrivilege_id() {
        return privilege_id;
    }

    public void setPrivilege_id(long privilege_id) {
        this.privilege_id = privilege_id;
    }
}
