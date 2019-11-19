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

import static otus.hw_12.cnf.hibernate.ConfigureHibernate.configureHibernate;


public class AdminServlet extends HttpServlet {
    private static final String DEFAULT_USER_NAME = "UNKNOWN";
    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";
    private static final String ADMIN_CREATE_HTML = "admin_create.html";
    private static final String ADMIN_GET_USERS = "admin_get_users.html";

    private final TemplateProcessor templateProcessor;
    private SessionFactory sessionFactory = configureHibernate();
    private DaoTemplate daoTemplate = new DaoTemplateImpl(sessionFactory);

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
            page = getAdminPageTemplate(ADMIN_CREATE_HTML, null);
        } else {
            Map<String, Object> pageVariables = createPageVariablesMap(request);
            page = getAdminPageTemplate(ADMIN_PAGE_TEMPLATE, pageVariables);
        }

        setResponse(response, page);
    }


    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("locale", request.getLocale());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());

        String login = (String) request.getSession().getAttribute("login");
        pageVariables.put("login", login != null ? login : DEFAULT_USER_NAME);

        return pageVariables;
    }

    private Map<String, Object> createUsersMap(List<User> userList) {
        if (userList.isEmpty()) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("user", "there is no users");
            return pageVariables;
        }
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("user", userList.get(0).toString());
        return pageVariables;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("getUsers") != null) {
            List<User> userList = daoTemplate.readAll();
            Map<String, Object> pageVariables = createUsersMap(userList);
            String page = getAdminPageTemplate(ADMIN_GET_USERS, pageVariables);
            setResponse(response, page);
        } else {
            String name = request.getParameterValues("user")[0];
            if (name != null) {
                User user = new User();
                user.setName(name);
                daoTemplate.create(user);
                doGet(request, response);
            } else {
                response.setStatus(404);
            }
        }
    }

    private String getAdminPageTemplate(String filename, Map<String, Object> data) throws IOException {
        return templateProcessor.getPage(filename, data);
    }

    private void setResponse(HttpServletResponse response, String page) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(page);
    }
}
