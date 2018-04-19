package xyz.frt.govern.service.impl;

import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.RoleMapper;
import xyz.frt.govern.model.Role;
import xyz.frt.govern.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author phw
 * @date Created in 04-19-2018
 * @description
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }
}
