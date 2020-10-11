package application.repositories;

import application.entities.event.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface IEventRepository extends CrudRepository<Event, Long> {

    List<Event> getEventsByDateBetweenOrderByDate(Date from, Date to);
    Event getFirstByFromBeforeAndToBefore(LocalTime from, LocalTime to);
}
