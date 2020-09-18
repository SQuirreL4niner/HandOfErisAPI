package com.handoferis.utils;

import static java.lang.String.format;
import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;

//public class EnvPropertiesUtils {
//
//    public static String getRequiredEnvProperty(EnvironmentPropertyName property) {
//        System.out.println("Hello");
//        System.out.println(System.getenv());
//        System.out.println(System.getProperties());
//        var value = ofNullable(getenv(property.name()));
//        var test = "hello";
//        return value.orElseThrow(() -> new IllegalStateException(format("Required environment property %s is missing." +
//                "Please read the manual to configure the app.", property)));
//    }
//}
