package otus.hw_13.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import otus.hw_13.jdbctemplate.DaoTemplate;
import otus.hw_13.jdbctemplate.DaoTemplateImpl;
import otus.hw_13.servlets.AdminServlet;
import otus.hw_13.servlets.LoginServlet;
import otus.hw_13.servlets.TemplateProcessor;

import java.util.Objects;

import static otus.hw_13.constants.Constants.LOGIN_VARIABLE_NAME;
import static otus.hw_13.utils.ConfigureHibernate.configureHibernate;

public class ServerUtil {

    public static Server createServer(int port) throws Exception {
        final String webDir = Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResource("public_html")).toExternalForm();

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(webDir);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        TemplateProcessor templateProcessor = new TemplateProcessor();
        DaoTemplate daoTemplate = new DaoTemplateImpl(configureHibernate());

        context.addServlet(new ServletHolder(new LoginServlet(templateProcessor, LOGIN_VARIABLE_NAME)), "/login");
        context.addServlet(new ServletHolder(new AdminServlet(templateProcessor, daoTemplate)), "/admin");

        Server server = new Server(port);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();

        return server;
    }
}
