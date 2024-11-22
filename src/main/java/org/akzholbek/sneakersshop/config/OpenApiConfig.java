package org.akzholbek.sneakersshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Sneakers shop"

                ),
                title = "Sneakers shop web and mobile app",
                description = "OpenApi documentation for Sneakers shop Spring Project"
        ),
        servers = {
                @Server(
                        description = "Server",
                        url = "localhost:9696"
                )
        }
)
public class OpenApiConfig {
}
