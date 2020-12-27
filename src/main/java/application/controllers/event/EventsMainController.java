package application.controllers.event;

import application.entities.event.Event;
import application.entities.user.User;
import application.menu.MenuTabs;
import application.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.EventType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsMainController {

    private final EventService eventService;

    @GetMapping
    public String events(@RequestParam(required = false, defaultValue = "")
                                     String eventType, Model model) {
        eventService.addEvents(eventType, model);
        return "events";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title,
                      @RequestParam String from,
                      @RequestParam String to,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                      @RequestParam String eventType,
                      @AuthenticationPrincipal User user,
                      Model model) {
        eventService.addNewEvent(title, from, to, date, eventType, user, model);
        if (model.asMap().isEmpty()) {
            return "redirect:/events";
        } else {
            Map<String, List<Event>> events = eventService.getWeekEvents(null);
            model.addAttribute("menuElements", MenuTabs.defaultMenu());
            model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
            model.addAttribute("eventTypes", EventType.values());
            model.addAttribute("events", events);
            return "events";
        }
    }

    @DeleteMapping("/delete/{event}")
    public String delete(@PathVariable Event event){
        eventService.deleteEvent(event);
        return "redirect:/events";
    }

}
