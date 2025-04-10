package org.example.travelappproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new io.swagger.v3.oas.models.Components()
                        // Google OAuth2 Security Scheme
                        .addSecuritySchemes("google-oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .implicit(new OAuthFlow()
                                                .authorizationUrl("https://accounts.google.com/o/oauth2/v2/auth")
                                                .scopes(new Scopes()
                                                        .addString("openid", "OpenID scope")
                                                        .addString("email", "Email scope")
                                                        .addString("profile", "Profile scope")))))
                        // Bearer Token Security Scheme
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                // Apply security requirements selectively
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
                        .addList("google-oauth2")
                        .addList("bearerAuth"));
    }
}