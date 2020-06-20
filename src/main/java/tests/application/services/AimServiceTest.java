package tests.application.services;

import application.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AimServiceTest {

    private static UserService userService;

    @BeforeAll
    public static void initialize(){
        userService = new UserService();
    }

    @Test
    void adaptAim() {
    }

    @Test
    void deleteAim() {
    }

    @Test
    void delete() {
    }

    @Test
    void achieve() {
    }

    @Test
    void editSmartAim() {
    }

    @Test
    void getAchievedUserAims() {
    }

    @Test
    void getNotDeletedUserAims() {
    }
}