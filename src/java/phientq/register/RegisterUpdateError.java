/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.register;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegisterUpdateError implements Serializable{
    private String passwordLengthViolent;
    private String passwordInvalidate;

    /**
     * @return the passwordLengthViolent
     */
    
    public RegisterUpdateError() {
    }

    public String getPasswordLengthViolent() {
        return passwordLengthViolent;
    }

    /**
     * @param passwordLengthViolent the passwordLengthViolent to set
     */
    public void setPasswordLengthViolent(String passwordLengthViolent) {
        this.passwordLengthViolent = passwordLengthViolent;
    }

    /**
     * @return the passwordInvalidate
     */
    public String getPasswordInvalidate() {
        return passwordInvalidate;
    }

    /**
     * @param passwordInvalidate the passwordInvalidate to set
     */
    public void setPasswordInvalidate(String passwordInvalidate) {
        this.passwordInvalidate = passwordInvalidate;
    }
    
    
}
