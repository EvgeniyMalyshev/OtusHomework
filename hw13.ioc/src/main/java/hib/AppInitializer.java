package hib;

import hib.configurations.SpringConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                SpringConfiguration.class
        };
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{

        };
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

