package application.repositories;

import application.entities.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IMessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);

    @Override
    <S extends Message> S save(S s);

    @Override
    <S extends Message> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Message> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<Message> findAll();

    @Override
    Iterable<Message> findAllById(Iterable<Integer> iterable);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Message message);

    @Override
    void deleteAll(Iterable<? extends Message> iterable);

    @Override
    void deleteAll();
}
