package com.acm;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.acm.service.activeprofile.ActiveProfile;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AcmAppMicroserviceApplication {

	public static void main(String[] args) {
		ActiveProfile activeProfile = new ActiveProfile();
		activeProfile.setPropertyFile();

		SpringApplication.run(AcmAppMicroserviceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.acm"))
				.paths(PathSelectors.any()).build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("ACM API", "API Documentation", "1.0", "Only for ACM users",
				new springfox.documentation.service.Contact("Samuel Butler", "https://acm-web.herokuapp.com/",
						"sambutler1017@icloud.com"),
				"API License", "https://www.linkedin.com/in/samuel-butler-178b2116b/", Collections.emptyList());
	}
}
