package otus.main;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import otus.config.IocConfig;
import otus.hibernate.DbConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class Appinitialaizer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                DbConfig.class,
                IocConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                IocConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}
