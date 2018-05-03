package xyz.frt.govern.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.ReportInfoMapper;
import xyz.frt.govern.model.ReportInfo;
import xyz.frt.govern.service.ReportInfoService;

@Service
@Transactional
public class ReportInfoServiceImpl extends BaseServiceImpl<ReportInfo> implements ReportInfoService {

    @Autowired
    private ReportInfoMapper reportInfoMapper;

    @Override
    public BaseMapper<ReportInfo> getMapper() {
        return reportInfoMapper;
    }

}
