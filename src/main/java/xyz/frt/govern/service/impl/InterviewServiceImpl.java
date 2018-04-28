package xyz.frt.govern.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.InterviewMapper;
import xyz.frt.govern.model.Interview;
import xyz.frt.govern.service.InterviewService;

@Service
@Transactional
public class InterviewServiceImpl extends BaseServiceImpl<Interview> implements InterviewService {

    @Autowired
    private InterviewMapper interviewMapper;

    @Override
    public BaseMapper<Interview> getMapper() {
        return interviewMapper;
    }
}
