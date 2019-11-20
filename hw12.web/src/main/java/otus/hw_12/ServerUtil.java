package otus.hw_12;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import otus.hw_12.servlets.AdminServlet;
import otus.hw_12.servlets.LoginServlet;
import otus.hw_12.servlets.TemplateProcessor;

import java.io.IOException;
import java.util.Objects;

public class ServerUtil {
    private final static int PORT = 8080;

    public static Server createServer() throws IOException {
        final String webDir = Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResource("public_html")).toExternalForm();

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(webDir);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        TemplateProcessor templateProcessor = new TemplateProcessor();

        context.addServlet(new ServletHolder(new LoginServlet(templateProcessor, "login")), "/login");
        context.addServlet(AdminServlet.class, "/admin");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
        return server;
    }
}
