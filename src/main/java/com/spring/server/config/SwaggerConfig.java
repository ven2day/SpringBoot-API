package com.spring.server.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.server.SpringBootApiConstants.ApiUrls;
import com.spring.server.SpringBootApiConstants.Tokens;

import lombok.RequiredArgsConstructor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig implements WebMvcConfigurer {

	private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference(Tokens.ACCESS_TOKEN_HEADER_NAME, authorizationScopes));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }

    private ApiKey apiKey() {
        return new ApiKey(Tokens.ACCESS_TOKEN_HEADER_NAME, 
        		Tokens.ACCESS_TOKEN_HEADER_NAME, "header");
    }
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API Document")
				.description("프로젝트 API 문서")
				.version("1.0.0").build();
	}

	@Bean
	public Docket swaggerAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false) // 기본 상태코드를 보여줄 것인가
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.apiInfo(apiInfo())
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant(ApiUrls.API_PREFIX + "/**"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	}

}