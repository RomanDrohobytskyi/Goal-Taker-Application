package application.services.event.strategy;

import application.entities.event.Event;

import java.util.List;
import java.util.Map;

public interface EventsService {
    Map<String, List<Event>> filterEvents();

}
