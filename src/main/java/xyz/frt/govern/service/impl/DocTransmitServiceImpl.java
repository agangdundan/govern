package xyz.frt.govern.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.DocTransmitMapper;
import xyz.frt.govern.model.DocTransmit;
import xyz.frt.govern.service.DocTransmitService;

@Service
@Transactional
public class DocTransmitServiceImpl extends BaseServiceImpl<DocTransmit> implements DocTransmitService {

    @Autowired
    private DocTransmitMapper docTransmitMapper;

    @Override
    public BaseMapper<DocTransmit> getMapper() {
        return docTransmitMapper;
    }
}
