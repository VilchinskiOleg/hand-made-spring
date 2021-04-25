package com.borisov.infrostrucrure.postprocessor;

import com.borisov.infrostrucrure.ApplicationContext;
import com.borisov.infrostrucrure.annotation.InjectObject;
import lombok.SneakyThrows;
import java.lang.reflect.Field;
import java.util.Arrays;

public class InjectObjectAnnotationPostProcessor implements PostProcessor {

    @Override
    public boolean support(Object instance) {
        return true;
    }

    @Override
    public void process(Object instance, ApplicationContext context) {
        Class<?> type = instance.getClass();
        Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InjectObject.class))
                .forEach(field -> setObjectInstanceToField(field, instance, context));
    }

    @SneakyThrows
    private void setObjectInstanceToField(Field field, Object instance, ApplicationContext context) {
        Object fieldInstance = context.getInstance(field.getType());
        field.setAccessible(true);
        field.set(instance, fieldInstance);
    }
}
