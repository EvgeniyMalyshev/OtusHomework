import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"java"})
public class Config {

    @Bean
    public TestAnnotationsCalculate calculateStatistic (){
        return new TestAnnotationsCalculate();
    }

    @Bean
    public Launcher launch(){
        return new Launcher();
    }

    @Bean
    public StatisticCounter count(){
        return new StatisticCounter();
    }


}
