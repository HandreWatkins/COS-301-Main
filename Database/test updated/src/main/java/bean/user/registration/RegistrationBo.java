/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.user.registration;

/**
 *
 * @author Xciver
 */
public interface RegistrationBo {
    /**
     * Register user method
     * @param username
     * @param password
     * @return 
     * 
     */
    public boolean registerUser(String username, String password);
}

