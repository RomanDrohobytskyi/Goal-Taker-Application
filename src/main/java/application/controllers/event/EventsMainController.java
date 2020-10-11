package application.controllers.event;

import application.entities.event.Event;
import application.entities.user.User;
import application.menu.MenuTabs;
import application.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class EventsMainController {

    private final EventService eventService;

    @GetMapping("/events")
    public String events(Model model) {
        Map<String, List<Event>> events = eventService.getSortedEventsForFullWeekFromTodayWithDay();
        model.addAttribute("events", events);
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "events";
    }

    @PostMapping("/events/add")
    public String add(@RequestParam String title,
                      @RequestParam String from,
                      @RequestParam String to,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                      @AuthenticationPrincipal User user,
                      Model model) {
        eventService.addNewEvent(title, from, to, date, user);
        return "redirect:/events";
    }

}
