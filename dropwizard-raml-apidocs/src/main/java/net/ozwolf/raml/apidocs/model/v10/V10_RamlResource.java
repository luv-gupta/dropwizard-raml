package net.ozwolf.raml.apidocs.model.v10;

import net.ozwolf.raml.apidocs.model.RamlMethod;
import net.ozwolf.raml.apidocs.model.RamlParameter;
import net.ozwolf.raml.apidocs.model.RamlResource;
import org.raml.v2.api.model.v10.resources.Resource;
import org.raml.v2.api.model.v10.system.types.AnnotableStringType;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

public class V10_RamlResource implements RamlResource {
    private final Resource resource;

    public V10_RamlResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String getDisplayName() {
        return Optional.ofNullable(resource.displayName()).map(AnnotableStringType::value).orElse(null);
    }

    @Override
    public String getDescription() {
        return Optional.ofNullable(resource.description()).map(AnnotableStringType::value).orElse(null);
    }

    @Override
    public String getPath() {
        return resource.resourcePath();
    }

    @Override
    public List<RamlParameter> getUriParameters() {
        return resource.uriParameters() == null ? newArrayList() : resource.uriParameters().stream().map(V10_RamlParameter::new).collect(toList());
    }

    @Override
    public List<RamlMethod> getMethods() {
        return resource.methods() == null ? newArrayList() : resource.methods().stream().map(V10_RamlMethod::new).collect(toList());
    }

    @Override
    public List<RamlResource> getResources() {
        return resource.resources() == null ? newArrayList() : resource.resources().stream().map(V10_RamlResource::new).collect(toList());
    }
}