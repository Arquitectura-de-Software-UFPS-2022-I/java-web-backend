package com.arquitectura.firmas.repository.dao;

import com.arquitectura.firmas.entities.Files;
import org.springframework.data.repository.CrudRepository;

public interface IFileDAo extends CrudRepository<Files,Integer> {
}
