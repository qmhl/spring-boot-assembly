package io.geekidea.springboot.assembly.demo.utils;

import com.example.demo.annotaion.HandlerOrderType;
import com.example.demo.service.OrderStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 自定义spring处理器。把所有的策略类存储到Map集合中。
 * </p>
 *
 * @author: heluwei
 * @Date: 2020/4/22 17:58
 */
@Component
public class HandlerOrderProcessor implements ApplicationContextAware {
    /**
     * 获取所有的策略Beanclass 加入HandlerOrderContext属性中
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //获取所有策略注解的Bean
        Map<String, Object> orderStrategyMap = applicationContext.getBeansWithAnnotation(HandlerOrderType.class);
        orderStrategyMap.forEach((k, v) -> {
            //获取策略实现类
            Class<OrderStrategy> orderStrategyClass = (Class<OrderStrategy>) v.getClass();
            //获取策略实现类的注解值。
            int type = orderStrategyClass.getAnnotation(HandlerOrderType.class).value();
            //将class加入HandlerOrderContext的map中,type作为key
            HandlerOrderContext.orderStrategyBeanMap.put(type, orderStrategyClass);
        });
    }
}

