package com.borisov.infrostrucrure.postprocessor;

import com.borisov.infrostrucrure.ApplicationContext;
import com.borisov.infrostrucrure.property.JavaProp;
import com.borisov.infrostrucrure.property.Prop;
import com.borisov.infrostrucrure.annotation.InjectProperty;
import lombok.SneakyThrows;
import java.lang.reflect.Field;
import java.util.Arrays;

public class InjectPropertiesPostProcessor implements PostProcessor {

    private final Prop prop = new JavaProp("properties.properties");

    @Override
    public boolean support(Object instance) {
        return true;
    }

    @Override
    public void process(Object instance, ApplicationContext context) {
        Class<?> type = instance.getClass();
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
