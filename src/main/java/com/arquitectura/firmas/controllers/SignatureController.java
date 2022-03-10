package com.arquitectura.firmas.controllers;

import com.arquitectura.firmas.repository.services.IFilesService;

import models.FileDto;
import models.FirmaDto;
import models.SignatureRequestDto;
import models.UserDto;
import services.impl.ApiService;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signature_requests")
public class SignatureController {
    @Autowired
    private IFilesService fileService;


   
    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception{
        return ResponseEntity.ok(new ApiService().getSignatureRequestService().getRequest());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) throws Exception{
        return ResponseEntity.ok(new ApiService().getSignatureRequestService().getRequestUser(id));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getSignatureUser(@PathVariable int id) throws Exception{
        return ResponseEntity.ok(new ApiService().getSignatureRequestService().getRequestId(id));
    }
    
    @PostMapping("/save")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> save(@RequestBody SignatureRequestDto firma) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiService().getSignatureRequestService().saveRequest(firma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody SignatureRequestDto firma) throws Exception{
        return ResponseEntity.ok(new ApiService().getSignatureRequestService().updateRequest(firma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id) throws Exception{
    	new ApiService().getSignatureRequestService().deleteRequest(id);
        return ResponseEntity.ok().build();
    }


}
