package application.services;

import application.test.AimRepositoryTest;
import application.test.SmartAim;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EntityInheritanceTest {


    @Autowired
    private AimRepositoryTest repo; //Mock - in case if field is static

    @Test
    void createSmartAim(){
        SmartAim smartAim = new SmartAim();
        smartAim.setTitle("Test2");
        smartAim.setText("Test2");
        smartAim.setSpecify("awd");
        //repo.save(smartAim);
    }
}
