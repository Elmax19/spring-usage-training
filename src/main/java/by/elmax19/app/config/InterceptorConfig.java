package by.elmax19.app.config;

import by.elmax19.app.controller.UrlPatterns;
import by.elmax19.app.intercoptor.LoggingDateInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final LoggingDateInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns(List.of(UrlPatterns.PLAYERS, UrlPatterns.SPECIFIC_PLAYER));
    }
}
