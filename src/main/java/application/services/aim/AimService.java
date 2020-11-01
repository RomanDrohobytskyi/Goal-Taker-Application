package application.services.aim;

import java.util.List;

public interface AimService<T> {
    T save(T aim);
    T delete(T aim);
    void delete(List<T> aim);
    T achieve(T aim);
}

