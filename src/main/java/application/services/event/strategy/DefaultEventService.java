package application.services.event.strategy;

import application.entities.event.Event;
import application.models.DayOfWeek;
import application.repositories.EventRepository;
import application.utils.date.DateInstances;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class DefaultEventService implements EventsService {

    private final EventRepository eventRepository;

    @Override
    public Map<String, List<Event>> filterEvents() {
        return getSortedEventsForFullWeekFromTodayWithDay();
    }

    public Map<String, List<Event>> getSortedEventsForFullWeekFromTodayWithDay() {
        List<String> weekDaysSortedFromToday = getWeekDaysStartsFromToday();
        Map<String, List<Event>> events = fillEmptyDaysOfWeek(weekDaysSortedFromToday);

        return weekDaysSortedFromToday.stream()
                .collect(toMap(day -> day, events::get));
    }

    public List<String> getWeekDaysStartsFromToday() {
        int dayOfWeek = new Date().getDay();

        DayOfWeek[] weekDaysFromToday = Arrays.copyOfRange(DayOfWeek.values(), dayOfWeek, 7);
        DayOfWeek[] weekDaysBeforeToday = Arrays.copyOfRange(DayOfWeek.values(), 0, dayOfWeek);

        DayOfWeek[] weekDays = Stream.of(weekDaysFromToday, weekDaysBeforeToday)
                .flatMap(Stream::of)
                .toArray(DayOfWeek[]::new);

        return Stream.of(weekDays)
                .map(DayOfWeek::getDay)
                .collect(toList());
    }

    private Map<String, List<Event>> fillEmptyDaysOfWeek(List<String> weekDaysSortedFromToday) {
        Map<String, List<Event>> events = getEventsForWeekFromTodayWithDay();
        weekDaysSortedFromToday.stream()
                .filter(day -> !events.containsKey(day))
                .forEach(day -> events.put(day, Collections.emptyList()));
        return events;
    }

    private Map<String, List<Event>> getEventsForWeekFromTodayWithDay() {
        return getEventsForWeekFromToday().stream()
                .collect(groupingBy(a -> a.getDayOfWeek().getDay()));
    }

    private List<Event> getEventsForWeekFromToday() {
        Date today = DateInstances.startOfDay(new Date());
        Date todayPlusSixDays = DateInstances.endOfDay(addDays(today, 6));
        return getEventsByDateBetweenOrderByDate(today, todayPlusSixDays)
                .orElse(Collections.emptyList());
    }

    private Date addDays(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

    private Optional<List<Event>> getEventsByDateBetweenOrderByDate(Date from, Date to) {
        return eventRepository.getEventsByDateBetweenOrderByDate(from, to);
    }
}
