package by.elmax19.commonsstarter.config;

import by.elmax19.commonsstarter.bean.FirstStarterInterface;
import by.elmax19.commonsstarter.bean.SecondStarterInterface;
import by.elmax19.commonsstarter.bean.impl.FirstInterfaceImpl;
import by.elmax19.commonsstarter.bean.impl.HelloWorldBean;
import by.elmax19.commonsstarter.bean.impl.SecondInterfaceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PropertyConfig.class)
public class StarterConfig {
    @Bean
    @ConditionalOnMissingBean
    public FirstStarterInterface firstStarterInterface() {
        return new FirstInterfaceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public SecondStarterInterface secondStarterInterface() {
        return new SecondInterfaceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public HelloWorldBean helloWorldBean(PropertyConfig config) {
        return new HelloWorldBean(config.getDisplayName());
    }
}
