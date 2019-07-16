package application.photos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhotosController {
    @GetMapping("/photos")
    public String photos() {
        return "photos";
    }
}
