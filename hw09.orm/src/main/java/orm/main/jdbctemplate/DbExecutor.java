package orm.main.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface DbExecutor {

    void update(String sql, List<Object> params) throws SQLException;
    <T> Optional<T> select(String sql, long id, Function<ResultSet, T> rsHandler);

}
