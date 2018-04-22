package xyz.frt.govern.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.DailyPatrolMapper;
import xyz.frt.govern.model.DailyPatrol;
import xyz.frt.govern.service.DailyPatrolService;

@Service
@Transactional
public class DailyPatrolServiceImpl extends BaseServiceImpl<DailyPatrol> implements DailyPatrolService {

    @Autowired
    private DailyPatrolMapper dailyPatrolMapper;

    @Override
    public BaseMapper<DailyPatrol> getMapper() {
        return dailyPatrolMapper;
    }
}
