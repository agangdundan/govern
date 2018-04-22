package xyz.frt.govern.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.FileMapper;
import xyz.frt.govern.model.File;
import xyz.frt.govern.service.FileService;

@Service
@Transactional
public class FileServiceImpl extends BaseServiceImpl<File> implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public BaseMapper<File> getMapper() {
        return fileMapper;
    }
}
