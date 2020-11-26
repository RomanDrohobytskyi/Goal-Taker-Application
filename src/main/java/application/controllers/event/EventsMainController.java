package application.controllers.event;

import application.entities.event.Event;
import application.entities.user.User;
import application.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("events")
@RequiredArgsConstructor
public class EventsMainController {

    private final EventService eventService;

    @GetMapping
    public String events(Model model) {
        eventService.addEventsAndMenu(model);
        return "events";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title,
                      @RequestParam String from,
                      @RequestParam String to,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                      @AuthenticationPrincipal User user,
                      Model model) {
        eventService.addNewEvent(title, from, to, date, user, model);
        if (model.asMap().isEmpty()) {
            return "redirect:/events";
        } else {
            eventService.addEventsAndMenu(model);
            return "events";
        }
    }

    @PostMapping("/delete/{event}")
    public String delete(@PathVariable Event event){
        eventService.deleteEvent(event);
        return "redirect:/events";
    }

}
