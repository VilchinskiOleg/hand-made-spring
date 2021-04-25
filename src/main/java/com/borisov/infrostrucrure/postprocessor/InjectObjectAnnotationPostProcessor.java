package com.borisov.infrostrucrure.postprocessor;

import com.borisov.infrostrucrure.ObjectFactory;
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
    public void process(Object instance) {
        Class<?> type = instance.getClass();
        Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InjectObject.class))
                .forEach(field -> setObjectInstanceToField(field, instance));
    }

    @SneakyThrows
    private void setObjectInstanceToField(Field field, Object instance) {
        Object fieldInstance = ObjectFactory.getFactoryInstance().createInstance(field.getType());
        field.setAccessible(true);
        field.set(instance, fieldInstance);
    }
}
