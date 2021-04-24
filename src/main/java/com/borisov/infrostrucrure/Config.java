package com.borisov.infrostrucrure;

public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> ifc);
 }
