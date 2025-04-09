package org.example.travelappproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("google-oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new io.swagger.v3.oas.models.security.OAuthFlows()
                                        .implicit(new io.swagger.v3.oas.models.security.OAuthFlow()
                                                .authorizationUrl("https://accounts.google.com/o/oauth2/v2/auth")
                                                .scopes(new io.swagger.v3.oas.models.security.Scopes()
                                                        .addString("openid", "OpenID scope")
                                                        .addString("email", "Email scope")
                                                        .addString("profile", "Profile scope"))))))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("google-oauth2"));
    }
}