package ex.aaronfae.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfiguration {

	/**
	 * 配置Feign使用Default的Contract
	 * 
	 * @return
	 */
	@Bean
	public Contract feignContract() {
		return new feign.Contract.Default();
	}

	/**
	 * 配置Eureka认证信息（如果Eureka中配置了认证，该Bean可以在请求Eureka apps信息时通过认证）
	 * 
	 * @return
	 */
	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("af", "123456");
	}
}
