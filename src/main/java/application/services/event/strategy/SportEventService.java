package application.services.event.strategy;

import application.entities.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static application.enums.EventType.SPORT;

@Service
@RequiredArgsConstructor
public class SportEventService implements EventsService {

    private final DefaultEventService defaultEventService;

    @Override
    public Map<String, List<Event>> filterEvents() {
        return getSportEvents();
    }

    private Map<String, List<Event>> getSportEvents() {
        Map<String, List<Event>> events = defaultEventService.getSortedEventsForFullWeekFromTodayWithDay();
        events.values()
                .forEach(eventsList -> eventsList.removeIf(event -> !isSportEvent(event)));
        return events;
    }

    private boolean isSportEvent(Event event) {
        return event.getType().equals(SPORT);
    }
}
