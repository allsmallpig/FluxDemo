package AppWare;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component

public class AppWareDemo implements ApplicationContextAware, InitializingBean {

    private ApplicationContext context;

    private Map<String, Object> beanMaps;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.beanMaps = context.getBeansOfType(Object.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
