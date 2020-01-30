package WebApp.dao;

import java.util.List;

public interface Dao <T>{
    T get(int id);

    List<T> getAll();

    void save(T item);

    void delete(int id);

    void update(T item);

}
