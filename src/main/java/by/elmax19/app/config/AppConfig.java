package by.elmax19.app.config;

import by.elmax19.app.bean.MyAnnotatedBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public static MyAnnotatedBean myAnnotatedBean() {
        return new MyAnnotatedBean();
    }
}
