package net.ozwolf.raml.generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.ozwolf.raml.annotations.RamlDescription;
import net.ozwolf.raml.annotations.RamlParameter;
import net.ozwolf.raml.annotations.RamlSecuredBy;
import net.ozwolf.raml.annotations.RamlTraits;
import net.ozwolf.raml.generator.factory.ResponseFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static net.ozwolf.raml.generator.util.CollectionUtils.nullIfEmpty;
import static org.apache.commons.lang3.StringUtils.trimToNull;

@JsonSerialize
@JsonPropertyOrder({"description", "securedBy", "is", "headers", "queryParameters", "responses"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RamlMethodModel {
    private final String action;
    private final String description;
    private final Set<String> securedBy;
    private final Set<String> traits;
    private final Map<String, RamlParameterModel> queryParameters;
    private final Map<String, RamlParameterModel> headers;
    private final Map<Integer, RamlResponseModel> responses;

    public RamlMethodModel(String action,
                           String description,
                           Set<String> securedBy,
                           Set<String> traits,
                           Map<String, RamlParameterModel> queryParameters,
                           Map<String, RamlParameterModel> headers,
                           Map<Integer, RamlResponseModel> responses) {
        this.action = action;
        this.description = description;
        this.securedBy = securedBy;
        this.traits = traits;
        this.queryParameters = queryParameters;
        this.headers = headers;
        this.responses = responses;
    }

    @JsonIgnore
    public String getAction() {
        return action;
    }

    @JsonProperty("description")
    public String getDescription() {
        return trimToNull(description);
    }

    @JsonProperty("securedBy")
    public Set<String> getSecuredBy() {
        return nullIfEmpty(securedBy);
    }

    @JsonProperty("is")
    public Set<String> getTraits() {
        return nullIfEmpty(traits);
    }

    @JsonProperty("queryParameters")
    public Map<String, RamlParameterModel> getQueryParameters() {
        return nullIfEmpty(queryParameters);
    }

    @JsonProperty("headers")
    public Map<String, RamlParameterModel> getHeaders() {
        return nullIfEmpty(headers);
    }

    @JsonProperty("responses")
    public Map<Integer, RamlResponseModel> getResponses() {
        return responses;
    }
}
