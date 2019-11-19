package otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;
import otus.hw_12.entity.Phone;
import otus.hw_12.entity.User;
import otus.hw_12.jdbctemplate.DaoTemplate;
import otus.hw_12.jdbctemplate.DaoTemplateImpl;
import otus.hw_12.servlets.AdminServlet;
import otus.hw_12.servlets.LoginServlet;
import otus.hw_12.servlets.TemplateProcessor;

import java.util.Objects;

public class MainTest {
    private final static int PORT = 8080;

    @Test
    public void mainTest() throws Exception {

        SessionFactory sessionFactory = configureHibernate();
        Assert.assertNotNull(sessionFactory);

        DaoTemplate daoTemplate = new DaoTemplateImpl(sessionFactory);
        Assert.assertNotNull(daoTemplate);

        final String webDir = Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResource("public_html")).toExternalForm();

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(webDir);
        Assert.assertNotNull(resourceHandler);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        TemplateProcessor templateProcessor = new TemplateProcessor();

        context.addServlet(new ServletHolder(new LoginServlet(templateProcessor, "anonymous")), "/login");
        context.addServlet(AdminServlet.class, "/admin");
        Assert.assertNotNull(context.getServletHandler());

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
        Assert.assertNotNull(server.getHandler());


        }
    private  SessionFactory configureHibernate() {

        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Phone.class)
                .getMetadataBuilder()
                .build();

        return metadata.buildSessionFactory();
    }
}
