package net.ozwolf.raml.generator;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;
import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.common.ValidationResult;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

class RamlGeneratorTest {
    @Test
    void shouldGenerateValidRAML() {
        String raml = new RamlGenerator("net.ozwolf.raml.generator.testapp", "1.0.0").generate();

        System.out.println(raml);

        RamlModelResult result = new RamlModelBuilder().buildApi(raml, "/");

        List<ValidationResult> errors = result.getValidationResults();

        assertThat(errors)
                .describedAs(makeError(errors))
                .isEmpty();
    }

    private String makeError(List<ValidationResult> errors) {
        List<String> lines = newArrayList("RAML validation failed for the following reasons:");
        errors.forEach(r -> lines.add(r.getMessage()));
        return Joiner.on("\n").join(lines);
    }
}