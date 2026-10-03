package com.lilithsthrone.LolificationProject.Magic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModInfo {
    String mod_author() default "DrZed";
    String mod_version() default "1.0";
    String mod_description() default "Business";
    String mod_name() default "Nunya";
    String mod_url() default "https://github.com";
    String mod_license() default "MIT";


}