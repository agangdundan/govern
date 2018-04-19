package xyz.frt.govern.model;

public class Role extends BaseEntity {

    private String roleDesc;

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

}