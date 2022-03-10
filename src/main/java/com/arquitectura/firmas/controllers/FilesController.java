package com.arquitectura.firmas.controllers;

import com.arquitectura.firmas.repository.services.IFilesService;

import models.FileDto;
import services.impl.ApiService;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FilesController {
    @Autowired
    private IFilesService fileService;


   
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody File file, @RequestParam String aux) throws Exception{
        return ResponseEntity.ok(new ApiService().getFileService().saveFile(aux, file));
    }
    
    @GetMapping("")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAll() throws Exception{
        return ResponseEntity.ok(new ApiService().getFileService().getFiles());
    }
    
    @GetMapping("/id/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getById(@PathVariable int id) throws Exception{
        return ResponseEntity.ok(new ApiService().getFileService().getFileId(id));
    }
    
    
    
    @GetMapping("/download")
    public ResponseEntity<?> getDocumentAsignature(@RequestParam String dir, @RequestParam int aux) throws Exception{
        new ApiService().getFileService().downloadFileSigned(dir,aux);
        return ResponseEntity.ok().build();
    
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) throws Exception{
    	new ApiService().getFileService().deleteFile(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/validar")
    public ResponseEntity<?> validar(@RequestBody File file) throws Exception{
        return ResponseEntity.ok(new ApiService().getFileService().validateAsignature(file));
    }
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable int id) throws Exception{
//    	new ApiService().getFileService().deleteFile(id);
//        return ResponseEntity.ok().build();
//    }
    
  


}
