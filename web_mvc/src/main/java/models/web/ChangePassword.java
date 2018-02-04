/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.web;

import javax.validation.constraints.Size;
import play.data.validation.Constraints;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class ChangePassword {

    
    @Constraints.Required
    @Size(min = 5)
    public String oldPassword;
    @Constraints.Required
    @Size(min = 5)
    public String newPassword;

    
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
