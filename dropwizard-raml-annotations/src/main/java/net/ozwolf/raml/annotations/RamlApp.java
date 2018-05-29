package net.ozwolf.raml.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RamlApp {
    String title();
    String description() default "";
    String[] protocols() default { "HTTPS" };
    String baseUri();
    RamlDocumentation[] documentation() default {};
    RamlSecurity[] security() default {};
    RamlTrait[] traits() default {};
}