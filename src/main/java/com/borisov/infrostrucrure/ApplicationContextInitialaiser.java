package com.borisov.infrostrucrure;

import com.borisov.infrostrucrure.config.Config;
import com.borisov.infrostrucrure.config.JavaConfig;
import java.util.Map;

public class ApplicationContextInitialaiser {

    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifsToClass) {
        Config config = new JavaConfig(packageToScan, ifsToClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        return context;
    }
}
