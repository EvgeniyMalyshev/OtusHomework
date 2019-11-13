package otus.hw_12.listeners;


import otus.hw_12.dbService.DBService;
import otus.hw_12.dbService.DBServiceImpl;
import otus.hw_12.entities.dataset.AddressDataSet;
import otus.hw_12.entities.dataset.PhoneDataSet;
import otus.hw_12.entities.dataset.UserDataSet;
import otus.hw_12.util.ehcache.EhCacheUtil;

import javax.cache.Cache;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@WebListener
public class AppInitializerListener implements ServletContextListener
{
    private DBService dbService;

    @Override
    public void contextInitialized(final ServletContextEvent sce)
    {
        try {
            Cache<Long, UserDataSet> cache = new EhCacheUtil().getUserDataSetCache();

            dbService = new DBServiceImpl(cache);

            String status = dbService.getLocalStatus();
            System.out.println("Status: " + status);

            List<AddressDataSet> addresses = getAddressList();

            for (int i = 0; i < 11; i++) {
                dbService.save(getUser("user" + i, addresses.get(new Random().nextInt(4))));
            }

            UserDataSet user = dbService.read(5);
            user.addAddress(new AddressDataSet("Waagh"));
            user.setName("Gorka");

            dbService.save(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce)
    {
        dbService.shutdown();
    }

    public static UserDataSet getUser(final String name, final AddressDataSet address)
    {
        UserDataSet user = new UserDataSet();
        user.setName(name);
        user.setAge(new Random().nextInt(50) + 15);
        user.addAddress(address);
        user.addPhoneNumber(new PhoneDataSet("00000 0000000"));
        return user;
    }

    public static List<AddressDataSet> getAddressList()
    {
        List<AddressDataSet> addresses = new ArrayList<>();
        addresses.add(new AddressDataSet("Bad Lane"));
        addresses.add(new AddressDataSet("Bad Str"));
        addresses.add(new AddressDataSet("Orc Str"));
        addresses.add(new AddressDataSet("BadMoon"));
        return addresses;
    }
}
