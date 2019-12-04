import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;
import otus.entity.Address;
import otus.entity.Phone;
import otus.entity.User;
import otus.jdbctemplate.DaoTemplate;
import otus.jdbctemplate.DaoTemplateImpl;

import java.util.List;

public class HibernateTest {

    @Test
    public void mainTest(){
        SessionFactory sessionFactory = configureHibernate();
        Assert.assertNotNull(sessionFactory);

        DaoTemplate daoTemplate = new DaoTemplateImpl(sessionFactory);
        Assert.assertNotNull(daoTemplate);

        Address address1 = new Address();
        address1.setStreet("Address1");
        Address address2 = new Address();
        address2.setStreet("Address2");

        Phone phone1 = new Phone();
        phone1.setNumber("+7");
        Phone phone2 = new Phone();
        phone2.setNumber("+8");
        Phone phone3 = new Phone();
        phone3.setNumber("+9");

        User userAdd = new User();
        userAdd.setName("User");

        User userOdd = new User();
        userOdd.setName("User");
        userOdd.setAge(32);
        userOdd.setAddress(address1);
        userOdd.setPhones(List.of(phone1, phone2));

        daoTemplate.create(userOdd);
        daoTemplate.createOrUpdate(userOdd);
        System.out.println(daoTemplate.load(1, User.class));
        System.out.println(daoTemplate.load(5, User.class));

        userOdd.setName("Василий");
        daoTemplate.update(userOdd);
        System.out.println(daoTemplate.load(1, User.class));
        System.out.println(daoTemplate.load(5, User.class));

        userOdd.setAge(11);
        userOdd.setAddress(address2);
        userOdd.setPhones(List.of(phone3));
        daoTemplate.createOrUpdate(userOdd);
        System.out.println(daoTemplate.load(1, User.class));
        System.out.println(daoTemplate.load(5, User.class));


    }

    private static SessionFactory configureHibernate(){
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Phone.class)
                .addAnnotatedClass(Address.class)
                .getMetadataBuilder()
                .build();

        return metadata.buildSessionFactory();
    }
}
