package application.repositories;

import application.entities.event.Event;
import application.models.DayOfWeek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    Optional<List<Event>> getEventsByDateBetweenOrderByDate(Date from, Date to);
    Event getFirstByFromBeforeAndToBeforeAndDayOfWeek(LocalTime from, LocalTime to, DayOfWeek dayOfWeek);
}
