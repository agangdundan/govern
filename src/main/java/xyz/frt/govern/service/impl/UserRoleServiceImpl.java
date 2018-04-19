package xyz.frt.govern.service.impl;

import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.UserRoleMapper;
import xyz.frt.govern.model.UserRole;
import xyz.frt.govern.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author phw
 * @date Created in 04-19-2018
 * @description
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseMapper<UserRole> getMapper() {
        return userRoleMapper;
    }
}
