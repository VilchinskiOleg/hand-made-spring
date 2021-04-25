package com.borisov.infrostrucrure.property;

import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class JavaProp implements Prop {

    private Map<String, String> properties;

    @SneakyThrows
    public JavaProp(String resourceName) {
        String path = ClassLoader
                        .getSystemClassLoader()
                        .getResource(resourceName)
                        .getPath();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        properties = reader.lines()
                .map(line -> line.split("="))
                .collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    public String readValueByKey(String key) {
        return properties.get(key);
    }
}
