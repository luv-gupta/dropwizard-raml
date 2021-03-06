package net.ozwolf.raml.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <h1>RAML Trait Annotation</h1>
 *
 * A way to describe a particular trait, including request headers, query parameters and responses.
 *
 * This can then be referenced using the `{@literal @}RamlIs` annotation on resource methods, allowing those methods to inherit the defined trait.
 *
 * @see RamlIs
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RamlTrait {
    /**
     * The trait key that is used by the `{@literal @}RamlIs` annotation.
     *
     * @return the trait key
     */
    String key();

    /**
     * Describe the usage situation of the trait
     *
     * @return the trait usage
     */
    String usage() default "";

    /**
     * Describe the trait
     *
     * @return the trait description
     */
    String description() default "";

    /**
     * The overall descriptor of the trait, covering required headers, query parameters and associated responses.
     *
     * @return the trait descriptor
     */
    RamlDescribedBy describedBy();
}
