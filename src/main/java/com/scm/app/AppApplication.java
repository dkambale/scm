package com.scm.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.scm.app.interceptor.AccessTokenInterceptor;

@SpringBootApplication
public class AppApplication {

	@Autowired
	private AccessTokenInterceptor accessTokenInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").
				allowedOriginPatterns("*")
				.allowedHeaders("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowCredentials(false)
						.maxAge(3600);
			}

			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(accessTokenInterceptor).addPathPatterns("/**") // Intercept all endpoints
						.excludePathPatterns("/login",          // Exclude login endpoint
                                "/swagger-ui/**",   // Exclude Swagger UI
                                "/v3/api-docs/**"); // Exclude login endpoint
			}
		};
	}

}
