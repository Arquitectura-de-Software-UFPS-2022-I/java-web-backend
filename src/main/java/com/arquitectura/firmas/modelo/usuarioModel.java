package com.arquitectura.firmas.modelo;

public class usuarioModel {
    private int idModel;
    private String emailModel;
    private String nameModel;
    private String usernameModel;
    private String passwordModel;
    private String rolModel;





    
    public usuarioModel(int idModel, String emailModel, String nameModel, String usernameModel, String passwordModel,
            String rolModel) {
        this.idModel = idModel;
        this.emailModel = emailModel;
        this.nameModel = nameModel;
        this.usernameModel = usernameModel;
        this.passwordModel = passwordModel;
        this.rolModel = rolModel;
    }
    public int getIdModel() {
        return idModel;
    }
    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }
    public String getEmailModel() {
        return emailModel;
    }
    public void setEmailModel(String emailModel) {
        this.emailModel = emailModel;
    }
    public String getNameModel() {
        return nameModel;
    }
    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }
    public String getUsernameModel() {
        return usernameModel;
    }
    public void setUsernameModel(String usernameModel) {
        this.usernameModel = usernameModel;
    }
    public String getPasswordModel() {
        return passwordModel;
    }
    public void setPasswordModel(String passwordModel) {
        this.passwordModel = passwordModel;
    }
    public String getRolModel() {
        return rolModel;
    }
    public void setRolModel(String rolModel) {
        this.rolModel = rolModel;
    }

    
    
}
