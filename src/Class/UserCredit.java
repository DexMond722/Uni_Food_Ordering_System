/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import Class.User;


/**
 *
 * @author desmondcwf
 */
public class UserCredit extends UserManager {

    private UserManager userManager;

    public UserCredit(UserManager userManager) {
        super();
        this.userManager = userManager;
    }

    public boolean topUpCredit(User user, double topUpAmount) {
        if (topUpAmount >= 0) {
            double currentCredit = user.getCredit();
            double updatedCredit = currentCredit + topUpAmount;
            user.setCredit(updatedCredit);

            userManager.saveUsers();

            return true;
        }
        return false;
    }
}


