package com.arquitectura.firmas.modelo;

import java.sql.Date;

public class FilesModel {
    private int idModel;

    private String nameModel;
    private byte[] fileModel;
    private Date createDateModel;

    
    public FilesModel(int idModel, String nameModel, byte[] fileModel, Date createDateModel) {
        this.idModel = idModel;
        this.nameModel = nameModel;
        this.fileModel = fileModel;
        this.createDateModel = createDateModel;
    }


    public int getIdModel() {
        return idModel;
    }


    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }


    public String getNameModel() {
        return nameModel;
    }


    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }


    public byte[] getFileModel() {
        return fileModel;
    }


    public void setFileModel(byte[] fileModel) {
        this.fileModel = fileModel;
    }


    public Date getCreateDateModel() {
        return createDateModel;
    }


    public void setCreateDateModel(Date createDateModel) {
        this.createDateModel = createDateModel;
    }

    

}
