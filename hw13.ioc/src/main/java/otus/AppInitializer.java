package otus;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import otus.configuration.SpringConfiguration;
import otus.hibernate.DbConfiguration;
import otus.rest.RestConfiguration;

@Slf4j
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RestConfiguration.class,
                DbConfiguration.class,
                SpringConfiguration.class
        };
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{};
    }

    @NotNull
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
