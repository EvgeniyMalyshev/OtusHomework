package otus.hw_12.servlets;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import otus.hw_12.entity.Phone;
import otus.hw_12.entity.User;
import otus.hw_12.jdbctemplate.DaoTemplate;
import otus.hw_12.jdbctemplate.DaoTemplateImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdminServlet extends HttpServlet {
    private static final String DEFAULT_USER_NAME = "UNKNOWN";
    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";
    private static final String ADMIN_CREATE_HTML = "admin_create.html";

    private final TemplateProcessor templateProcessor;


    @SuppressWarnings("WeakerAccess")
    public AdminServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @SuppressWarnings("WeakerAccess")
    public AdminServlet() throws IOException {
        this(new TemplateProcessor());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        if (request.getSession().getAttribute("login") != null && request.getSession().getAttribute("password") != null) {
            page = templateProcessor.getPage(ADMIN_CREATE_HTML, null);
        } else {
            Map<String, Object> pageVariables = createPageVariablesMap(request);
            page = templateProcessor.getPage(ADMIN_PAGE_TEMPLATE, pageVariables);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(page);
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("locale", request.getLocale());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());

        //let's get login from session
        String login = (String) request.getSession().getAttribute("login");
        pageVariables.put("login", login != null ? login : DEFAULT_USER_NAME);

        return pageVariables;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("user");
        String phone = request.getParameter("phone");
        String users = request.getParameter("getUsers");
        SessionFactory sessionFactory = configureHibernate();
        DaoTemplate daoTemplate = new DaoTemplateImpl(sessionFactory);
        if (users!=null){
            daoTemplate.readAll();
        }
        else if (name != null && phone != null) {
            User user = new User();
            user.setName(name);
            Phone phone1 = new Phone();
            phone1.setNumber(phone);
            user.setPhones(List.of(phone1));
            daoTemplate.create(user);
        }
        else {
            response.setStatus(403);
        }

    }

    private static SessionFactory configureHibernate() {
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
