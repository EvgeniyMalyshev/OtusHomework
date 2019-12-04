package otus.hw_13.jdbctemplate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import otus.hw_13.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.List;

public class DaoTemplateImpl implements DaoTemplate {

    private final SessionFactory sessionFactory;

    public DaoTemplateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public <T> void create(T object) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.save(object);
                session.getTransaction().commit();
            } catch (RuntimeException ex) {
                session.getTransaction().rollback();
                ex.printStackTrace();
            }
        }
    }

    @Override
    public <T> void update(T object) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(object);
                session.getTransaction().commit();
            } catch (RuntimeException ex) {
                session.getTransaction().rollback();
                ex.printStackTrace();
            }
        }
    }

    @Override
    public <T> T load(long id, Class<T> clazz) {
        T selected = null;
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                selected = session.get(clazz, id);
            } catch (RuntimeException ex) {
                session.getTransaction().rollback();
                ex.printStackTrace();
            }
        }
        return selected;
    }

    @Override
    public <T> void createOrUpdate(T object) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.saveOrUpdate(object);
                session.getTransaction().commit();
            } catch (RuntimeException ex) {
                session.getTransaction().rollback();
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<User> readAll() {
        try (Session session = sessionFactory.openSession()) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<User> criteria = builder.createQuery(User.class);
                criteria.from(User.class);
                return session.createQuery(criteria).list();
            } catch (RuntimeException ex) {
                session.getTransaction().rollback();
                ex.printStackTrace();
            }

        }
        return Collections.emptyList();
    }

}