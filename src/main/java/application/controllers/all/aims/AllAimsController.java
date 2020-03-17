package application.controllers.all.aims;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllAimsController {

    @GetMapping("/aims")
    public String allAims(){
        return "aims";
    }
}
