package application.gretting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingController {

    //First page
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
}
