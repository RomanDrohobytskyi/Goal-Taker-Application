package application.services.event.strategy;


import application.entities.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static application.enums.EventType.ART;

@Service
@RequiredArgsConstructor
public class ArtEventService implements EventsService {

    private final DefaultEventService defaultEventService;

    @Override
    public Map<String, List<Event>> filterEvents() {
        return getArtEvents();
    }

    private Map<String, List<Event>> getArtEvents() {
        Map<String, List<Event>> events = defaultEventService.getSortedEventsForFullWeekFromTodayWithDay();
        events.values()
                .forEach(eventsList -> eventsList.removeIf(event -> !isArtEvent(event)));
        return events;
    }

    private boolean isArtEvent(Event event) {
        return event.getType().equals(ART);
    }
}
