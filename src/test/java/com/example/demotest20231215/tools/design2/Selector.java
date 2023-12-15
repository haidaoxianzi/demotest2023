package com.example.demotest20231215.tools.design2;

//import org.jetbrains.annotations.NotNull;
//import com.sun.istack.internal.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: gina
 * @Date: 2023-11-09 09:05
 * @Description:
 */


/**
 * 策略选择器
 */
@Component
public class Selector implements ApplicationContextAware {

    private Map<String, BaseClass> selectorMap;

    public BaseClass select(String type) {
        return selectorMap.get(type);
    }
   // @NotNull
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.selectorMap = applicationContext.getBeansOfType(BaseClass.class).values().stream()
                .filter(strategy -> strategy.getClass().isAnnotationPresent(SelectorAnno.class))
                .collect(Collectors.toMap(strategy -> strategy.getClass().getAnnotation(SelectorAnno.class).value(), strategy -> strategy));
    }
}
