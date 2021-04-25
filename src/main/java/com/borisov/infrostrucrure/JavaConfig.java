package com.borisov.infrostrucrure;

import org.reflections.Reflections;
import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {

    private Reflections scanner;
    private Map<Class, Class> ifcToImplClass;

    public JavaConfig(String packageToScan, Map<Class, Class> ifcToImplClass) {
        scanner = new Reflections(packageToScan);
        this.ifcToImplClass = ifcToImplClass;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        return ifcToImplClass.computeIfAbsent(ifc, key -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(key);
            if (classes.size() != 1) {
                throw new RuntimeException(key + " has 0 or more one impl pleas update your config");
            }
            return classes.iterator().next();
        });
    }
}
