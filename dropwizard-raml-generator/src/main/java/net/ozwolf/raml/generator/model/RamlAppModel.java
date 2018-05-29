package net.ozwolf.raml.generator.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.ozwolf.raml.annotations.RamlApp;
import net.ozwolf.raml.annotations.RamlIgnore;
import net.ozwolf.raml.annotations.RamlSecurity;
import net.ozwolf.raml.annotations.RamlTrait;
import org.reflections.Reflections;

import javax.ws.rs.Path;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static net.ozwolf.raml.generator.util.CollectionUtils.nullIfEmpty;
import static org.apache.commons.lang3.StringUtils.trimToNull;

@JsonSerialize
@JsonPropertyOrder({"title", "description", "version", "protocols", "baseUri", "documentation", "securitySchemes", "traits"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RamlAppModel {
    private final String title;
    private final String description;
    private final String version;
    private final Set<String> protocols;
    private final String baseUri;
    private final List<RamlDocumentationModel> documentation;
    private final Map<String, RamlSecurityModel> securitySchemes;
    private final Map<String, RamlDescribedByModel> traits;

    private final Map<String, RamlResourceModel> resources;

    public RamlAppModel(String version,
                        RamlApp annotation,
                        Reflections reflections) {
        this.title = annotation.title();
        this.description = annotation.description();
        this.version = version;
        this.protocols = newHashSet(annotation.protocols());
        this.baseUri = annotation.baseUri();
        this.documentation = Arrays.stream(annotation.documentation()).map(RamlDocumentationModel::new).collect(toList());
        this.securitySchemes = Arrays.stream(annotation.security()).collect(toMap(RamlSecurity::key, RamlSecurityModel::new));
        this.traits = Arrays.stream(annotation.traits()).collect(toMap(RamlTrait::key, a -> new RamlDescribedByModel(a.describedBy())));

        this.resources = reflections.getTypesAnnotatedWith(Path.class).stream().filter(t -> !t.isAnnotationPresent(RamlIgnore.class)).map(RamlResourceModel::new).collect(toMap(RamlResourceModel::getPath, v -> v));
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return trimToNull(description);
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("protocols")
    public Set<String> getProtocols() {
        return protocols;
    }

    @JsonProperty("baseUri")
    public String getBaseUri() {
        return baseUri;
    }

    @JsonProperty("documentation")
    public List<RamlDocumentationModel> getDocumentation() {
        return nullIfEmpty(documentation);
    }

    @JsonProperty("securitySchemes")
    public Map<String, RamlSecurityModel> getSecuritySchemes() {
        return nullIfEmpty(securitySchemes);
    }

    @JsonProperty("traits")
    public Map<String, RamlDescribedByModel> getTraits() {
        return nullIfEmpty(traits);
    }

    @JsonAnyGetter
    public Map<String, RamlResourceModel> getResources() {
        return resources;
    }

    public static class ResourcesSerializer extends JsonSerializer<Map<String, RamlResourceModel>> {
        @Override
        public void serialize(Map<String, RamlResourceModel> resources, JsonGenerator generator, SerializerProvider provider) throws IOException {
            for (Map.Entry<String, RamlResourceModel> resource : resources.entrySet()) {
                generator.writeObjectField(resource.getKey(), resource.getValue());
            }
        }
    }
}