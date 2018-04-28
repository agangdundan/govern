package xyz.frt.govern.config;

import xyz.frt.govern.common.*;
import xyz.frt.govern.model.Role;
import xyz.frt.govern.model.UserRole;
import xyz.frt.govern.service.RoleService;
import xyz.frt.govern.service.UserRoleService;
import io.jsonwebtoken.SignatureException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author phw
 * @date Created in 04-08-2018
 * @description
 */
@Service
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Override
    public Class getAuthenticationTokenClass() {
        return JWTToken.class;//This realm only support JWTToken
    }

    /**
     * 重写Shiro获取用户信息的方法
     * @param authenticationToken token
     * @return 用户信息
     * @throws AuthenticationException e
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JWTToken jwtToken = (JWTToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();

        //Some problems
        Map<String, Object> map;
        try {
             map = JWTUtil.parserJwt(token);
        } catch (SignatureException e) {
            //e.printStackTrace();
            throw new UnauthorizedException(401, e.getMessage());
        }
        //Update user's token


        return new SimpleAuthenticationInfo(map, Boolean.TRUE, getName());
    }

    /**
     * 只用当用户需要检测权限的时候才会用到这个方法
     * @param principalCollection c
     * @return authorization info
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Map<String, String>> list = principalCollection.asList();
        Map<String, String> dataMap = list.get(0);
        if (BaseUtils.isNullOrEmpty(dataMap)) {
            return null;
        }
        String id = dataMap.get(AppConst.KEY_ID);
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("user_id", Integer.valueOf(id));
        List<UserRole> userRoleList = userRoleService.selectByConditions(conditions);
        if (BaseUtils.isNullOrEmpty(userRoleList)) {
            return null;
        }
        UserRole userRole = userRoleList.get(0);
        String rolesStr = userRole.getRoleIds();
        String[] roleIds = rolesStr.split(",");
        Set<String> roleSet = new HashSet<>();
        for (String roleId: roleIds) {
            Role role = roleService.selectByPrimaryKey(Integer.valueOf(roleId));
            roleSet.add(role.getRoleDesc());
        }
        info.setRoles(roleSet);
        return info;
    }
}
