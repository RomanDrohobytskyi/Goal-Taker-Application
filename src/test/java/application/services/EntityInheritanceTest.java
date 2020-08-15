package application.services;

import application.test.AimRepositoryTest;
import application.test.SmartAim;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("EntityInheritanceTest")
class EntityInheritanceTest {

    @Autowired
    private static AimRepositoryTest repo;

    @Test
    void createSmartAim(){
        SmartAim smartAim = new SmartAim();
        smartAim.setText("Test2");
        smartAim.setSpecify("awd");
        repo.save(smartAim);
    }
}
