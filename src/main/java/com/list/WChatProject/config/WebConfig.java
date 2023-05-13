package com.list.WChatProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("https://web-wchat-project-fe-7hqac2alhi4ekqp.sel4.cloudtype.app/")
				.allowedMethods(
						HttpMethod.GET.name(),
						HttpMethod.HEAD.name(),
						HttpMethod.POST.name(),
						HttpMethod.PUT.name(),
						HttpMethod.DELETE.name());
	}
}
