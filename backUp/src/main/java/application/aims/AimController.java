package application.aims;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AimController {

    @GetMapping("/aim")
    public String aim(){
        return "aim";
    }
}
