package application.services.event;

import application.entities.event.Event;
import application.entities.user.User;
import application.enums.EventType;
import application.menu.MenuTabs;
import application.models.DayOfWeek;
import application.repositories.EventRepository;
import application.services.event.strategy.*;
import application.utils.date.TimeParser;
import lombok.RequiredArgsConstructor;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static application.enums.EventType.NONE;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private Model model;

    public Event addNewEvent(String title, String from, String to, Date date, String eventType, User user, Model model) {
        this.model = model;
        Event event = adaptEvent(title, from, to, date, eventType, user);
        return validateEvent(event) ? save(event) : event;
    }

    public boolean validateEvent(Event event) {
        if (!validateAvailableHours(event)) {
            model.addAttribute("message", "This time is already taken for another event!");
            return false;
        }
        return true;
    }

    public boolean validateAvailableHours(Event event) {
        Event first = eventRepository.getFirstByFromBeforeAndToBeforeAndDayOfWeekAndDate(
                event.getFrom(), event.getTo(), event.getDayOfWeek(), event.getDate());
        return first == null;
    }

    public Event adaptEvent(String title, String from, String to, Date date, String eventType, User user) {
        LocalTime fromParsed = TimeParser.parseToLocalTime(from).orElseThrow(IllegalArgumentException::new);
        LocalTime toParsed = TimeParser.parseToLocalTime(to).orElseThrow(IllegalArgumentException::new);
        DayOfWeek dayOfWeek = getDayOfWeekByDayNumber(date.getDay());
        return buildEvent(title, fromParsed, toParsed, date, dayOfWeek, EventType.valueOf(eventType), user);
    }

    public DayOfWeek getDayOfWeekByDayNumber(int dayNumber) {
        return DayOfWeek.values()[dayNumber];
    }

    public Event buildEvent(String title, LocalTime from, LocalTime to, Date date, DayOfWeek dayOfWeek, EventType eventType, User user) {
        return Event.builder()
                .title(title)
                .from(from)
                .to(to)
                .date(date)
                .dayOfWeek(dayOfWeek)
                .user(user)
                .creationDate(new Date())
                .type(eventType)
                .build();
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void addEvents(String eventType, Model model) {
        model.addAttribute("menuElements", MenuTabs.defaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
        model.addAttribute("eventTypes", EventType.getEventsTypes());
        model.addAttribute("events", getWeekEvents(eventType));
    }

    public Map<String, List<Event>> getWeekEvents(String eventType) {
        EventType type = StringUtils.isBlank(eventType) ? NONE : EventType.valueOf(eventType);
        return getWeekEvents(getEventsService(type));
    }

    private Map<String, List<Event>> getWeekEvents(EventsService strategyEventService) {
        return strategyEventService.filterEvents();
    }

    private EventsService getEventsService(EventType eventType) {
        switch (eventType) {
            case SPORT:
                return new SportEventService(new DefaultEventService(eventRepository));
            case ART:
                return new ArtEventService(new DefaultEventService(eventRepository));
            case EDUCATION:
                return new EducationEventService(new DefaultEventService(eventRepository));
            case NONE:
            default:
                return new DefaultEventService(eventRepository);
        }
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }
}
