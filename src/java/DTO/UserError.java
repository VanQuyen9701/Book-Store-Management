/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ACER
 */
public class UserError {

    private String userIdError;
    private String nameError;
    private String addressError;
    private String roleIdError;
    private String passwordError;
    private String confirmPasswordError;

    public UserError() {
    }

    public UserError(String userIdError, String nameError, String addressError, String roleIdError, String passwordError, String confirmPasswordError) {
        this.userIdError = userIdError;
        this.nameError = nameError;
        this.addressError = addressError;
        this.roleIdError = roleIdError;
        this.passwordError = passwordError;
        this.confirmPasswordError = confirmPasswordError;
    }

    public String getUserIdError() {
        return userIdError;
    }

    public void setUserIdError(String userIdError) {
        this.userIdError = userIdError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getRoleIdError() {
        return roleIdError;
    }

    public void setRoleIdError(String roleIdError) {
        this.roleIdError = roleIdError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

}
