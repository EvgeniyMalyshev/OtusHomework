package otus.hw_13.servlets;

import otus.hw_13.entity.User;
import otus.hw_13.jdbctemplate.DaoTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static otus.hw_13.constants.Constants.ADMIN_CREATE_HTML;
import static otus.hw_13.constants.Constants.ADMIN_GET_USERS;
import static otus.hw_13.constants.Constants.ADMIN_PAGE_TEMPLATE;
import static otus.hw_13.constants.Constants.DEFAULT_USER_NAME;
import static otus.hw_13.constants.Constants.ERROR;
import static otus.hw_13.constants.Constants.LOCALE;
import static otus.hw_13.constants.Constants.LOGIN_PARAMETER_NAME;
import static otus.hw_13.constants.Constants.METHOD;
import static otus.hw_13.constants.Constants.PARAMETERS;
import static otus.hw_13.constants.Constants.PASSWORD_PARAMETER_NAME;
import static otus.hw_13.constants.Constants.SESSION_ID;
import static otus.hw_13.constants.Constants.USER;
import static otus.hw_13.constants.Constants.USER_REQUEST_URL;
import static otus.hw_13.constants.Constants.UTF_8_TEMPLATE;


public class AdminServlet extends HttpServlet {

    private final TemplateProcessor templateProcessor;
    private final DaoTemplate daoTemplate;

    public AdminServlet(TemplateProcessor templateProcessor, DaoTemplate daoTemplate) {
        this.templateProcessor = templateProcessor;
        this.daoTemplate = daoTemplate;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page;
        if (request.getSession().getAttribute(LOGIN_PARAMETER_NAME) != null && request.getSession().getAttribute(PASSWORD_PARAMETER_NAME) != null) {
            page = getAdminPageTemplate(ADMIN_CREATE_HTML, null);
        } else {
            Map<String, Object> pageVariables = createPageVariablesMap(request);
            page = getAdminPageTemplate(ADMIN_PAGE_TEMPLATE, pageVariables);
        }

        setResponse(response, page);
    }


    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(METHOD, request.getMethod());
        pageVariables.put(USER_REQUEST_URL, request.getRequestURL().toString());
        pageVariables.put(LOCALE, request.getLocale());
        pageVariables.put(SESSION_ID, request.getSession().getId());
        pageVariables.put(PARAMETERS, request.getParameterMap().toString());

        String login = (String) request.getSession().getAttribute(LOGIN_PARAMETER_NAME);
        pageVariables.put(LOGIN_PARAMETER_NAME, login != null ? login : DEFAULT_USER_NAME);

        return pageVariables;
    }

    private Map<String, Object> createUsersMap(List<User> userList) {
        if (userList.isEmpty()) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put(USER, "there is no users");
            return pageVariables;
        }
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(USER, userList.toString());
        return pageVariables;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("getUsers") != null) {
            List<User> userList = daoTemplate.readAll();
            Map<String, Object> pageVariables = createUsersMap(userList);
            String page = getAdminPageTemplate(ADMIN_GET_USERS, pageVariables);
            setResponse(response, page);
        } else {
            String name = request.getParameterValues(USER)[0];
            if (name != null) {
                User user = new User(name);
                daoTemplate.create(user);
                doGet(request, response);
            } else {
                response.setStatus(Integer.parseInt(ERROR));
            }
        }
    }

    private String getAdminPageTemplate(String filename, Map<String, Object> data) throws IOException {
        return templateProcessor.getPage(filename, data);
    }

    private void setResponse(HttpServletResponse response, String page) throws IOException {
        response.setContentType(UTF_8_TEMPLATE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(page);
    }
}
