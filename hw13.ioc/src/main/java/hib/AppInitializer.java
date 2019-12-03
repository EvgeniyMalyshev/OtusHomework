package hib;


import hib.configurations.SpringConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


@Slf4j
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

