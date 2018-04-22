package xyz.frt.govern.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.dao.AreaNoticeMapper;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.model.AreaNotice;
import xyz.frt.govern.service.AreaNoticeService;

@Service
@Transactional
public class AreaNoticeServiceImpl extends BaseServiceImpl<AreaNotice> implements AreaNoticeService {

    @Autowired
    private AreaNoticeMapper areaNoticeMapper;

    @Override
    public BaseMapper<AreaNotice> getMapper() {
        return areaNoticeMapper;
    }
}
