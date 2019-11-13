package otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Assert;
import org.junit.Test;
import otus.hw_12.listeners.AppInitializerListener;
import otus.hw_12.servlets.AdminServlet;
import otus.hw_12.servlets.LoginServlet;
import otus.hw_12.servlets.TemplateProcessor;

import javax.servlet.ServletContextListener;
import java.util.Objects;

public class MainTest {
    private final static int PORT = 8080;

    @Test
    public void mainTest() throws Exception {
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

        ServletContextListener scl = new AppInitializerListener();
        context.addEventListener(scl);
        Assert.assertNotNull(context.getEventListeners());

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
        Assert.assertNotNull(server.getHandler());
    }
}
