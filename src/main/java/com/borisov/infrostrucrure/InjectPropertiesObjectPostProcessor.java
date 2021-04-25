package com.borisov.infrostrucrure;

import com.borisov.infrostrucrure.annotation.InjectProperty;
import lombok.SneakyThrows;
import java.lang.reflect.Field;
import java.util.Arrays;

public class InjectPropertiesObjectPostProcessor implements ObjectPostProcessor {

    private final Prop prop;

    public InjectPropertiesObjectPostProcessor(String resourceName) {
        this.prop = new JavaProp(resourceName);
    }

    @Override
    public void process(Object instance, Class<?> type) {
        Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InjectProperty.class))
                .forEach(field -> setPropValueToField(field, instance));
     }

    @SneakyThrows
    private void setPropValueToField(Field field, Object instance) {
        field.setAccessible(true);
        String value = field.getAnnotation(InjectProperty.class).value();
        if (value.isEmpty()) {
            field.set(instance, prop.readValueByKey(field.getName()));
        } else {
            field.set(instance, prop.readValueByKey(value));
        }
    }
}
