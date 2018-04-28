package xyz.frt.govern.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.RolePermissionMapper;
import xyz.frt.govern.model.RolePermission;
import xyz.frt.govern.service.RolePermissionService;

@Service
@Transactional
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public BaseMapper<RolePermission> getMapper() {
        return rolePermissionMapper;
    }
}
