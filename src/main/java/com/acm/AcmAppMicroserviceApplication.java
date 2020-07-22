package com.acm;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class AcmAppMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmAppMicroserviceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.marcs"))
				.paths(PathSelectors.any()).build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Marc's API", "API Documentation", "23.0", "Only for Marc's users",
				new springfox.documentation.service.Contact("Samuel Butler", "https://marcs-web.herokuapp.com/",
						"sambutler1017@icloud.com"),
				"API License", "https://www.linkedin.com/in/samuel-butler-178b2116b/", Collections.emptyList());
	}
}
