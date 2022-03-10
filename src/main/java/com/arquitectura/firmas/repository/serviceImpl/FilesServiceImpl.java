package com.arquitectura.firmas.repository.serviceImpl;

import com.arquitectura.firmas.entities.Files;
import com.arquitectura.firmas.repository.dao.IFileDAo;
import com.arquitectura.firmas.repository.services.IFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesServiceImpl implements IFilesService {
    @Autowired
    private IFileDAo fileDao;

    @Override
    public Files save(Files files) {
        return fileDao.save(files);
    }

    @Override
    public List<Files> findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Files findById(int id) {
        return null;
    }

    @Override
    public Files update(Files files, int id) {
        return null;
    }
}
