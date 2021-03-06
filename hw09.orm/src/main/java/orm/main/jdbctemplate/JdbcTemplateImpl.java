package orm.main.jdbctemplate;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateImpl implements JdbcTemplate {

    private final DbExecutor executor;
    private final Map<Class, EntityDecomposite> decomposites = new HashMap<>();

    public JdbcTemplateImpl(Connection connection) {
        this.executor = new DbExecutorImpl(connection);
    }

    @Override
    public <T> void create(T object) throws SQLException {
        if (object != null) {
            EntityDecomposite entityDecomposite = findOrCreateEntityDecomposite(object.getClass());
            if(entityDecomposite.correctEntityClass)
                executor.update(entityDecomposite.getInsertSql(), entityDecomposite.getParamsWithoutId(object));
        } else {
            System.out.println("create is not executed");
        }
    }

    @Override
    public <T> void update(T object) throws SQLException {
        if (object != null) {
            EntityDecomposite entityDecomposite = findOrCreateEntityDecomposite(object.getClass());
            if(entityDecomposite.correctEntityClass) {
                List<Object> params = entityDecomposite.getParamsWithoutId(object);
                params.add(entityDecomposite.getId(object));
                executor.update(entityDecomposite.getUpdateSql(), params);
            }
        } else {
            System.out.println("update is not executed");
        }
    }

    @Override
    public <T> T load(long id, Class<T> clazz) throws SQLException {
        if(clazz != null){
            EntityDecomposite entityDecomposite = findOrCreateEntityDecomposite(clazz);
            if(entityDecomposite.correctEntityClass){
                Optional<T> result = executor.select(entityDecomposite.getSelectByIdSql(), id, resultSet -> {
                    T resultObject = null;
                    try {
                        if (resultSet.next()) {
                            Object[] values = entityDecomposite.getAllFieldsList().stream()
                                    .map(it-> {
                                        try {
                                            return resultSet.getObject(it.getName());
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                            return null;
                                        }
                                    }).toArray();
                            resultObject = (T) entityDecomposite.getConstructor().newInstance(values);
                        }
                    } catch (IllegalAccessException | InstantiationException | SQLException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return resultObject;
                });
                return result.orElse(null);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public <T> void createOrUpdate(T object) throws SQLException {
        if(object != null){
            EntityDecomposite entityDecomposite = findOrCreateEntityDecomposite(object.getClass());
            if(entityDecomposite.correctEntityClass){
                if(isExists(entityDecomposite.getId(object), object.getClass())) {
                    update(object);
                } else {
                    create(object);
                }
            }
        } else {
            System.out.println("createOrUpdate is not executed");
        }
    }

    @Override
    public boolean isExists(long id, Class clazz) throws SQLException {
        return Optional.ofNullable(findOrCreateEntityDecomposite(clazz))
                .filter(e -> e.correctEntityClass)
                .flatMap(e -> executor.select(e.getSelectForExistsSql(), id, resultSet -> {
                            try {
                                return (resultSet.next() ?  new Object() : null);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            return null;
                        })
                ).isPresent();
    }

    private EntityDecomposite findOrCreateEntityDecomposite(Class clazz){
        if(clazz == null) return null;
        EntityDecomposite entityDecomposite = decomposites.getOrDefault(clazz, new EntityDecomposite(clazz));
        decomposites.put(clazz, entityDecomposite);
        return entityDecomposite;
    }
}