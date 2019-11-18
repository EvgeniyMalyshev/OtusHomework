package otus.hw_12.listeners;

import otus.hw_12.jdbctemplate.DaoTemplate;
import otus.hw_12.jdbctemplate.DaoTemplateImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitializerListener implements ServletContextListener
{
    private DaoTemplate dbService;

    @Override
    public void contextInitialized(final ServletContextEvent sce)
    {
        try {

           // dbService = new DaoTemplateImpl();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce)
    {

    }
}
