package otus.hw_13.jdbctemplate;

import otus.hw_13.entity.User;

import java.util.List;

public interface DaoTemplate {

    <T> void create(T object);
    <T> void update(T object);
    <T> T load(long id, Class<T> clazz);
    <T> void createOrUpdate(T object);
    List<User> readAll();

}
