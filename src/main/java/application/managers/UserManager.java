package application.managers;

import application.entities.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

public class UserManager {

    @Transactional
    public User getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")){
            return null;
        }
        return (User) authentication.getPrincipal();
    }

    public boolean isLoggedUserAdmin(){
        return getLoggedInUser() != null && getLoggedInUser().isAdmin();
    }
}
