package net.ozwolf.raml.example;

import net.ozwolf.raml.annotations.*;

@RamlApp(
        title = "DropWizard Example App",
        description = "An example application documented using the RAML annotations.",
        protocols = "HTTP",
        baseUri = "http://localhost:8888",
        documentation = {
                @RamlDocumentation(title = "Summary", content = "apispecs/documetnation/summary.md"),
                @RamlDocumentation(title = "Annotations", content = "apispecs/documetnation/annotations.md")
        },
        security = {
                @RamlSecurity(
                        key = "oauth2",
                        type = "OAuth 2.0",
                        description = "application supports OAuth 2.0 tokens through LinkedIn",
                        describedBy = @RamlDescriptor(
                                headers = @RamlParameter(
                                        name = "Authorization",
                                        type = "string",
                                        description = "used to send valid OAuth 2.0 access token",
                                        pattern = "Bearer (.+)"
                                ),
                                responses = {
                                        @RamlResponse(
                                                status = 401,
                                                description = "user token is not active or expired",
                                                bodies = @RamlBody(
                                                        contentType = "application/json",
                                                        schema = "apispecs/resources/errors/schemas/standard-error-response.json",
                                                        example = "apispecs/resources/errors/examples/not-authorized-response.json"
                                                )
                                        ),
                                        @RamlResponse(
                                                status = 403,
                                                description = "user is forbidden from accessing the resource",
                                                bodies = @RamlBody(
                                                        contentType = "application/json",
                                                        schema = "apispecs/resources/errors/schemas/standard-error-response.json",
                                                        example = "apispecs/resources/errors/examples/forbidden-response.json"
                                                )
                                        ),
                                        @RamlResponse(
                                                status = 503,
                                                description = "security is unable to currently handle request",
                                                bodies = @RamlBody(
                                                        contentType = "application/json",
                                                        schema = "apispecs/resources/errors/schemas/standard-error-response.json",
                                                        example = "apispecs/resources/errors/examples/security-unavailable-response.json"
                                                )
                                        )
                                }
                        )
                ),
                @RamlSecurity(
                        key = "user-token",
                        type = "x-user-token",
                        description = "a token provided by the application's own security provider",
                        describedBy = @RamlDescriptor(
                                headers = @RamlParameter(
                                        name = "Authorization",
                                        type = "string",
                                        description = "used to send a valid user token",
                                        pattern = "User (.+)"
                                ),
                                responses = {
                                        @RamlResponse(
                                                status = 401,
                                                description = "user token is not active or expired",
                                                bodies = @RamlBody(
                                                        contentType = "application/json",
                                                        schema = "apispecs/resources/errors/schemas/standard-error-response.json",
                                                        example = "apispecs/resources/errors/examples/not-authorized-response.json"
                                                )
                                        ),
                                        @RamlResponse(
                                                status = 403,
                                                description = "user is forbidden from accessing the resource",
                                                bodies = @RamlBody(
                                                        contentType = "application/json",
                                                        schema = "apispecs/resources/errors/schemas/standard-error-response.json",
                                                        example = "apispecs/resources/errors/examples/forbidden-response.json"
                                                )
                                        ),
                                        @RamlResponse(
                                                status = 503,
                                                description = "security is unable to currently handle request",
                                                bodies = @RamlBody(
                                                        contentType = "application/json",
                                                        schema = "apispecs/resources/errors/schemas/standard-error-response.json",
                                                        example = "apispecs/resources/errors/examples/security-unavailable-response.json"
                                                )
                                        )
                                }
                        )
                )
        },
        traits = {
                @RamlTrait(
                        key = "has400",
                        usage = "apply this to any method that will return a bad request response",
                        description = "some requests may result in a bad request error",
                        describedBy = @RamlDescriptor(
                                responses = @RamlResponse(
                                        status = 400,
                                        description = "request could not be deserialized",
                                        bodies = @RamlBody(
                                                contentType = "application/json",
                                                schema = "apispecs/resources/errors/schemas/standard-error-response.json",
                                                example = "apispecs/resources/errors/examples/bad-request-response.json"
                                        )
                                )
                        )
                ),
                @RamlTrait(
                        key = "has404",
                        usage = "apply this to any method that will return a not found response",
                        description = "some requests may result in a not found",
                        describedBy = @RamlDescriptor(
                                responses = @RamlResponse(
                                                status = 404,
                                                description = "resource could not be found",
                                                bodies = @RamlBody(
                                                        contentType = "application/json",
                                                        schema = "apispecs/resources/errors/schemas/standard-error-response.json",
                                                        example = "apispecs/resources/errors/examples/not-found-response.json"
                                                )
                                        )
                        )
                ),
                @RamlTrait(
                        key = "validated",
                        usage = "apply this to any method whose request is validated",
                        description = "request bodies will be validated and return an error response if it fails",
                        describedBy = @RamlDescriptor(
                                responses = @RamlResponse(
                                        status = 422,
                                        description = "request failed validation",
                                        bodies = @RamlBody(
                                                contentType = "application/json",
                                                schema = "apispecs/resources/errors/schemas/validation-error-response.json",
                                                example = "apispecs/resources/errors/examples/validation-error-response.json"
                                        )
                                )
                        )
                )
        }
)
public final class ExampleAppSpecs {
}
