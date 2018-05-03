package xyz.frt.govern.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.dao.AreaMapper;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.model.Area;
import xyz.frt.govern.service.AreaService;

@Service
@Transactional
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public BaseMapper<Area> getMapper() {
        return areaMapper;
    }
}
