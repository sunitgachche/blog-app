package com.sunit.blog.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	
	
	 @Bean
	    public GroupedOpenApi publicApi() {
	        return GroupedOpenApi.builder()
	                .group("public")
	                .pathsToMatch("/api/**")
	                
	                .build();
	    }
	 

	    @Bean
	    public OpenAPI customOpenAPI() {
	      final String securitySchemeName = "bearerAuth";
	     
	      return new OpenAPI()
	    		  
	    		  .info(new Info().title("Blogging Application : Backend Course")
	  	                .description("This code is developed by Sunit Gachche")
	  	                .version("v 1.0")
	  	                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	  	                .externalDocs(new ExternalDocumentation()
	  	                .description("SpringShop Wiki Documentation")
	  	                .url("https://springshop.wiki.github.org/docs"))
	          .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
	          .components(
	              new Components()
	                  .addSecuritySchemes(securitySchemeName,
	                      new SecurityScheme()
	                          .name(securitySchemeName)
	                          .type(SecurityScheme.Type.HTTP)
	                          .scheme("bearer")
	                          .bearerFormat("JWT")
	                  )
	          ) ;
	          
	    }

}
