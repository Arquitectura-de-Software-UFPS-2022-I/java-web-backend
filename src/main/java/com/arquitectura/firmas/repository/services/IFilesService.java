package com.arquitectura.firmas.repository.services;

import com.arquitectura.firmas.entities.Files;

import java.util.List;

public interface IFilesService {

    Files save(Files files);
    List<Files> findAll();
    void delete(int id);
    Files findById(int id);
    Files update(Files files, int id);
}
