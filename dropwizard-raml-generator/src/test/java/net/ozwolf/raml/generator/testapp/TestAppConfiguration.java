package net.ozwolf.raml.generator.testapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.dropwizard.Configuration;

@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestAppConfiguration extends Configuration {

}
