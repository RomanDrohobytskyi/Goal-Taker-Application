package application.services;

import application.entities.event.Event;
import application.entities.user.User;
import application.menu.MenuTabs;
import application.repositories.IEventRepository;
import application.utils.DayOfWeek;
import application.utils.date.DateInstances;
import application.utils.date.TimeParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EventService {
    private final IEventRepository eventRepository;
    private Model model;

    public Event addNewEvent(String title, String from, String to, Date date, User user, Model model) {
        this.model = model;
        Event event = adaptEvent(title, from, to, date, user);
        if (validateEvent(event)) {
            save(event);
        }
        return event;
    }

    public boolean validateEvent(Event event) {
        if (!validateAvailableHours(event)) {
            model.addAttribute("message", "This time is already taken for another event!");
            return false;
        }
        return true;
    }

    public boolean validateAvailableHours(Event event) {
        Event first = eventRepository.getFirstByFromBeforeAndToBeforeAndDayOfWeek(event.getFrom(), event.getTo(), event.getDayOfWeek());
        return first == null;
    }

    public Event adaptEvent(String title, String from, String to, Date date, User user) {
        LocalTime fromParsed = TimeParser.parseToLocalTime(from).orElseThrow(IllegalArgumentException::new);
        LocalTime toParsed = TimeParser.parseToLocalTime(to).orElseThrow(IllegalArgumentException::new);
        DayOfWeek dayOfWeek = getDayOfWeekByDayNumber(date.getDay());
        Optional<Event> event = buildEvent(title, fromParsed, toParsed, date, dayOfWeek, user);
        return event.orElseThrow(NullPointerException::new);
    }

    public DayOfWeek getDayOfWeekByDayNumber(int dayNumber) {
        return DayOfWeek.values()[dayNumber];
    }

    public Optional<Event> buildEvent(String title, LocalTime from, LocalTime to, Date date, DayOfWeek dayOfWeek, User user) {
        Event event = Event.builder()
                .title(title)
                .from(from)
                .to(to)
                .date(date)
                .dayOfWeek(dayOfWeek)
                .user(user)
                .creationDate(new Date())
                .build();
        return Optional.ofNullable(event);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Map<String, List<Event>> getSortedEventsForFullWeekFromTodayWithDay() {
        List<String> weekDaysSortedFromToday = getWeekDaysStartsFromToday();
        Map<String, List<Event>> events = new HashMap<>(getEventsForWeekFromTodayWithDay());
        Map<String, List<Event>> sortedEventsForWeekFromToday = new LinkedHashMap<>();

        for (String day : weekDaysSortedFromToday) {
            if (!events.containsKey(day)) {
                events.put(day, Collections.emptyList());
            }
        }

        for (String day : weekDaysSortedFromToday) {
            sortedEventsForWeekFromToday.put(day, events.get(day));
        }

        return sortedEventsForWeekFromToday;
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

    public Map<String, List<Event>> getEventsForWeekFromTodayWithDay() {
        return getEventsForWeekFromToday().stream()
                .collect(groupingBy(a -> a.getDayOfWeek().getDay()));
    }

    public List<Event> getEventsForWeekFromToday() {
        Date today = DateInstances.startOfDay(new Date());
        Date todayPlusSixDays = DateInstances.endOfDay(addDays(today, 6));
        return getEventsByDateBetweenOrderByDate(today, todayPlusSixDays)
                .orElseThrow(NullPointerException::new);
    }

    private Date addDays(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

    public Optional<List<Event>> getEventsByDateBetweenOrderByDate(Date from, Date to) {
        List<Event> events = eventRepository.getEventsByDateBetweenOrderByDate(from, to);
        return Optional.ofNullable(events);
    }

    public void addEventsAndMenu(Model model) {
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        Map<String, List<Event>> events = getSortedEventsForFullWeekFromTodayWithDay();
        model.addAttribute("events", events);
    }
}
