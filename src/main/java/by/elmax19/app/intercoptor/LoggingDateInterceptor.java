package by.elmax19.app.intercoptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
@Slf4j
public class LoggingDateInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request \"" + request.getRequestURI() + "\"(" + request.getMethod() + ") has been sent " + LocalDateTime.now());
        return true;
    }
}
