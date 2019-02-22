package application.gretting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    //First page
    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }
}
