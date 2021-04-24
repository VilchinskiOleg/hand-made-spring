package com.borisov.infrostrucrure;

import org.reflections.Reflections;
import java.util.Set;

public class JavaConfig implements Config {

    private Reflections scanner;

    public JavaConfig(String packageToScan) {
        scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        if (classes.size() != 1) {
            throw new RuntimeException(ifc + " has 0 or more one impl");
        }
        return classes.iterator().next();
    }
}
